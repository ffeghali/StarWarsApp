package com.ffeghali.starwarsapp.ui;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.HashSet;
import java.util.Set;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    SharedPreferences sharedPreferences;
    private TextView nameTV;
    private boolean fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // SHARED PREFERENCES
        sharedPreferences = getSharedPreferences("char_favs", MODE_PRIVATE);
        Set<String> retrievedSet = sharedPreferences.getStringSet("hashSetKey", new HashSet<String>());

        // RETRIEVE DATA FROM OTHER ACTIVITY
        String name = getIntent().getStringExtra("NAME");
        Log.d(TAG, name);
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
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(name);
                    editor.apply();
                }
                onBackPressed();
                //TODO Shared pref not saving correctly
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
        ListView charDetailsList = findViewById(R.id.charDetails);
        String[] details = {"Item 1", "Item 2", "Item 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, details);
        charDetailsList.setAdapter(adapter);




    }
}