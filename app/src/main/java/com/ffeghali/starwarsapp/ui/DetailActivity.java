package com.ffeghali.starwarsapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.ffeghali.starwarsapp.R;

public class DetailActivity extends AppCompatActivity {
    private TextView nameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set up Name
        String name = getIntent().getStringExtra("NAME");
        nameTV = findViewById(R.id.nameTV);
        nameTV.setText(name);
    }
}