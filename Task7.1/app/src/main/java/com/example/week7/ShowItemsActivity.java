package com.example.week7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShowItemsActivity extends AppCompatActivity {

    List<Item> itemList;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        gson = new Gson();

        Intent intent = getIntent();
        String itemListJson = intent.getStringExtra("itemList");
        Type listType = new TypeToken<List<Item>>() {}.getType();
        itemList = gson.fromJson(itemListJson, listType);

        if (itemList == null) {
            itemList = new ArrayList<>();
        }

        ListView itemsListView = findViewById(R.id.itemsListView);
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        itemsListView.setAdapter(adapter);

        itemsListView.setOnItemClickListener((parent, view, position, id) -> {
            Item selectedItem = itemList.get(position);

            Intent detailsIntent = new Intent(ShowItemsActivity.this, ItemDetailsActivity.class);
            detailsIntent.putExtra("itemList", selectedItem);
            startActivity(detailsIntent);
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }
}



