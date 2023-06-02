package com.example.week10;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {
    private TextView textPrice;
    private Button buttonBook;
    private Button buttonCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        textPrice = findViewById(R.id.text_price);
        buttonBook = findViewById(R.id.button_book);
        buttonCall = findViewById(R.id.button_call);

        int estimatedPrice = getIntent().getIntExtra("estimated_price", 0);
        textPrice.setText("Estimated Price: " + estimatedPrice + " RMB");

        buttonBook.setOnClickListener(v -> {
            try {
                // Launch Google Pay
                Uri uri = Uri.parse("https://pay.google.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(MapActivity.this, "Google Pay app not found", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCall.setOnClickListener(v -> {
            Uri uri = Uri.parse("tel:" + "1234567890");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        });
    }
}


