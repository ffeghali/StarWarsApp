package com.ffeghali.starwarsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;


import com.ffeghali.starwarsapp.model.CharacterModel;
import com.ffeghali.starwarsapp.R;
import com.ffeghali.starwarsapp.viewmodel.MainViewModel;


import java.io.IOException;
import okhttp3.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ArrayList<CharacterModel> characterModels = new ArrayList<>();
    private Set<String> uids = new HashSet<>();
    private SearchView searchBar;
    private AppCompatButton btn; //todo delete

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.makeQuery().observe(this, new androidx.lifecycle.Observer<ResponseBody>() {
            @Override
            public void onChanged(ResponseBody responseBody) {
                Log.d(TAG, "onChanged: this is a live data response!");
                try {
                    Log.d(TAG, "onChanged: " + responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        /*searchBar = findViewById(R.id.searchBar);
        searchBar.clearFocus();*/

        /*RecyclerView recyclerView = findViewById(R.id.cRecyclerView);
        recyclerView.setHasFixedSize(true);
        setUpCharacterModels();
        C_RecyclerViewAdapter adapter = new C_RecyclerViewAdapter(this,
                characterModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TODO testing, delete later
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRequest();
            }
        });*/
    }


    /*public void testRequest () {
        fetchDataFromSwapi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            // Handle the result (JSON) here
                            Log.d("SWAPI", result);
                        },
                        throwable -> {
                            // Handle error here
                            Log.e("SWAPI", "Error fetching data", throwable);
                        }
                );
        }*/

    /*private Observable<String> fetchDataFromSwapi () {
        return Observable.create((ObservableEmitter<String> emitter) -> {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("https://swapi.dev/api/people/");
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
    }*/

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