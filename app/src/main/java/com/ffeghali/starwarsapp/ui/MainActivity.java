package com.ffeghali.starwarsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;

import com.ffeghali.starwarsapp.data.models.CharacterModel;
import com.ffeghali.starwarsapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<CharacterModel> characterModels = new ArrayList<>();
    private Set<String> uids = new HashSet<>();
    private SearchView searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.searchBar);
        searchBar.clearFocus();

        RecyclerView recyclerView = findViewById(R.id.cRecyclerView);
        //recyclerView.setHasFixedSize(true);
        setUpCharacterModels();
        C_RecyclerViewAdapter adapter = new C_RecyclerViewAdapter(this,
                characterModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Creates an ArrayList of CharacterModel objects based off live search results
     * in the SearchView in MainActivity.
     * TODO fix this description after API and viewmodel integration, this will prob be a viewmodel method
     */
    private void setUpCharacterModels(){
        String[] characterNames = new String[]{"Luke Skywalker", "R2-D2"};
        boolean[] favorite = new boolean[]{false, false};

        for (int i = 0; i<characterNames.length; i++){
            characterModels.add(new CharacterModel(characterNames[i],
                    favorite[i]));
        }
    }
}