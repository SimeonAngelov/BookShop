package com.example.daskalski.warcraftbookstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView welcomeTV = (TextView) findViewById(R.id.welcomeTV);
        welcomeTV.setText("Welcome, " + getIntent().getStringExtra("loggedUser"));

    }
}
