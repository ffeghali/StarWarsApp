package com.ffeghali.starwarsapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ffeghali.starwarsapp.R;
import com.ffeghali.starwarsapp.model.CharacterModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private C_RecyclerViewAdapter adapter;
    private ArrayList<CharacterModel> charactersList = new ArrayList<>();
    private Set<String> uids = new HashSet<>();
    private SearchView searchView;
    private Disposable searchDisposable;
    private final PublishSubject<String> searchSubject = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RECYCLER VIEW
        RecyclerView recyclerView = findViewById(R.id.cRecyclerView);
        recyclerView.setHasFixedSize(true);
        setUpCharacterModels();
        adapter = new C_RecyclerViewAdapter(this, charactersList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // SEARCH VIEW
        searchView = findViewById(R.id.searchBar);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;  // todo
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchSubject.onNext(newText);
                return true;
            }
        });
        setUpSearchObservable();

        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    private void setUpSearchObservable() {
        searchDisposable = searchSubject
                .debounce(300, TimeUnit.MILLISECONDS) // This waits until there's a 300ms pause in input to send request
                .distinctUntilChanged()  // Only make a new request if the query changed
                .switchMap(query -> fetchDataForQuery(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(e -> Log.e(TAG, "Error fetching data", e))
                )
                .subscribe(
                        result -> {
                            // Extract the results array from json string
                            Gson gson = new Gson();
                            JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();

                            // Converting json Object into a list of CharacterModel objects
                            Type listType = new TypeToken<ArrayList<CharacterModel>>(){}.getType();
                            charactersList = gson.fromJson(jsonObject.get("results"), listType);
                            //Send new list to adapter to update the recycler view
                            adapter.setSearchedList(charactersList);
                        },
                        throwable -> {
                            // Handle error here
                            Log.e(TAG, "Error in the chain", throwable);
                        }
                );
    }

    private Observable<String> fetchDataForQuery(String query) {
        return Observable.create(emitter -> {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("https://swapi.dev/api/people/?search=" + query);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                StringBuilder response = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                emitter.onNext(response.toString());
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            } finally {
                try {
                    if (reader != null) reader.close();
                    if (connection != null) connection.disconnect();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (searchDisposable != null && !searchDisposable.isDisposed()) {
            searchDisposable.dispose();
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Creates an ArrayList of CharacterModel objects based off live search results
     * in the SearchView in MainActivity.
     * TODO fix this description after API and viewmodel integration, this will prob be a viewmodel method
     */
    private void setUpCharacterModels() {
        /*String[] characterNames = new String[]{"Luke Skywalker", "R2-D2"};
        boolean[] favorite = new boolean[]{false, false};

        for (int i = 0; i<characterNames.length; i++){
            characterModels.add(new CharacterModel(characterNames[i],
                    favorite[i]));
        }*/

    }

}