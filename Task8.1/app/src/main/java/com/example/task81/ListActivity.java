package com.example.task81;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listViewUrls);

        SharedPreferences sharedPreferences = getSharedPreferences("url_info", MODE_PRIVATE);
        String json = sharedPreferences.getString("url_list", null);

        List<String> urlList;

        if (json == null) {
            urlList = new ArrayList<>();
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<List<String>>(){}.getType();
            urlList = gson.fromJson(json, type);
        }

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, urlList);
        listView.setAdapter(arrayAdapter);
    }
}


