package com.ffeghali.starwarsapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.ffeghali.starwarsapp.R;
import com.ffeghali.starwarsapp.model.CharacterModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    SharedPreferences sharedPreferences;
    private TextView nameTV;
    private CharacterModel character;
    private String name, height, mass, hair, skin, eye, birthdate, gender;
    private boolean fav;
    private Set<String> retrievedSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // SHARED PREFERENCES
        sharedPreferences = getSharedPreferences("char_favs", MODE_PRIVATE);
        retrievedSet = sharedPreferences.getStringSet("hashSetKey", new HashSet<String>());

        // RETRIEVE AND FORMAT DATA FROM OTHER ACTIVITY
        character = getIntent().getParcelableExtra("character");
        name = character.getName();
        height = character.getHeight();
        if(height == null || height == "n/a") {height="";}
        mass = character.getMass();
        if(mass == null || mass == "n/a") {mass="";}
        hair = character.getHair();
        if(hair == null || hair == "n/a") {hair="";}
        skin = character.getSkin();
        if(skin == null || skin == "n/a") {skin="";}
        eye = character.getEye();
        if(eye == null || eye == "n/a") {eye="";}
        birthdate = character.getBirthdate();
        if(birthdate == null || birthdate == "n/a") {birthdate="";}
        gender = character.getGender();
        if(gender == null || gender == "n/a") {gender="";}
        if (retrievedSet.contains(name)) {fav = true;}
        else{fav = false;}

        //TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //BACK BUTTON
        ImageView backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save shared preferences before leaving activity
                // Add fav to shared pref if it was clicked
                if(fav && !retrievedSet.contains(name)){
                    retrievedSet.add(name);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putStringSet("hashSetKey", retrievedSet);
                    editor.apply();
                }
                // Remove fav from shared pre if it was unclick
                if(!fav && retrievedSet.contains(name)) {
                    retrievedSet.remove(name);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putStringSet("hashSetKey", retrievedSet);
                    editor.apply();
                }
                onBackPressed();
            }
        });
        // CHARACTER NAME
        nameTV = findViewById(R.id.nameTV);
        nameTV.setText(name);
        // FAV BUTTON
        ImageView favBtn = (ImageView) findViewById(R.id.favBtn);
        if(fav){favBtn.setImageResource(R.drawable.baseline_favorite_24);}
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change fav status on click
                if(fav){
                    favBtn.setImageResource(R.drawable.baseline_favorite_border_24);
                    fav = false;

                } else {
                    favBtn.setImageResource(R.drawable.baseline_favorite_24);
                    fav = true;
                }
            }
        });

        // CHARACTER DETAILS
        // TODO I WOULD CHANGE THIS INTO A RECYCLER VIEW
        ListView charDetailsList = findViewById(R.id.charDetails);
        String[] details = {"Height: "+ height+"cm", "Mass: "+ mass +"kg",
                "Hair Color: "+hair,  "Skin Color: "+ skin,
                "Eye Color: "+ eye, "Birthday: "+ birthdate, "Gender: "+gender};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, details);
        charDetailsList.setAdapter(adapter);




    }
}