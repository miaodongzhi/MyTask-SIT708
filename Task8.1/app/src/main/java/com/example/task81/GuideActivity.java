package com.example.task81;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private EditText editTextUrl;
    private Button buttonPlay;
    private Button buttonAddToList;
    private Button buttonViewList;

    List<String> urlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        editTextUrl = findViewById(R.id.editTextUrl);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonAddToList = findViewById(R.id.buttonAddToList);
        buttonViewList = findViewById(R.id.buttonViewList);
        urlList = new ArrayList<>();

        buttonPlay.setOnClickListener(view -> {
            String url = editTextUrl.getText().toString();
            Intent intent = new Intent(GuideActivity.this, PlayActivity.class);
            intent.putExtra("URL", url);
            startActivity(intent);
        });

        buttonAddToList.setOnClickListener(view -> {
            String url = editTextUrl.getText().toString();
            urlList.add(url);
            Gson gson = new Gson();
            String json = gson.toJson(urlList);
            SharedPreferences sharedPreferences = getSharedPreferences("url_info", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("url_list", json);
            editor.apply();
        });

        buttonViewList.setOnClickListener(view -> {
            Intent intent = new Intent(GuideActivity.this, ListActivity.class);
            startActivity(intent);
        });
    }
}

