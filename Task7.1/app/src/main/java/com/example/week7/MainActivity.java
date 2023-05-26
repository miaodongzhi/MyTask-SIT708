package com.example.week7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();

        Button createAdvertButton = findViewById(R.id.create_advert_button);
        createAdvertButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateAdvertActivity.class);
            intent.putExtra("itemList", (Serializable) itemList);
            startActivity(intent);
        });

        Button showItemsButton = findViewById(R.id.show_items_button);
        showItemsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShowItemsActivity.class);
            intent.putExtra("itemList", (Serializable) itemList);
            startActivity(intent);
        });
    }
}


