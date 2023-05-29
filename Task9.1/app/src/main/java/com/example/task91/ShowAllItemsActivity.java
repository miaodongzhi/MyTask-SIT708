package com.example.task91;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShowAllItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_items);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.task91", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("itemList", null);
        Type typeOfItemList = new TypeToken<List<Item>>(){}.getType();
        List<Item> itemList = gson.fromJson(json, typeOfItemList);

        ItemAdapter adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);



    }
}

