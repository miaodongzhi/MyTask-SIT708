package com.example.task91;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCreateAdvert, btnShowAllItems, btnShowOnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateAdvert = findViewById(R.id.btnCreateAdvert);
        btnShowAllItems = findViewById(R.id.btnShowAllItems);
        btnShowOnMap = findViewById(R.id.btnShowOnMap);

        btnCreateAdvert.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateAdvertActivity.class);
            startActivity(intent);
        });

        btnShowAllItems.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ShowAllItemsActivity.class);
            startActivity(intent);
        });

        btnShowOnMap.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ShowOnMapActivity.class);
            startActivity(intent);
        });
    }
}

