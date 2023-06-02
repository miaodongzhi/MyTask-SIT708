package com.example.week10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EstimateActivity extends AppCompatActivity {
    private EditText editStart;
    private EditText editEnd;
    private EditText editSize;
    private EditText editWeight;
    private Button buttonEstimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate);

        editStart = findViewById(R.id.edit_start);
        editEnd = findViewById(R.id.edit_end);
        editSize = findViewById(R.id.edit_size);
        editWeight = findViewById(R.id.edit_weight);
        buttonEstimate = findViewById(R.id.button_estimate);

        buttonEstimate.setOnClickListener(v -> {
            // Get user inputs...
            String start = editStart.getText().toString();
            String end = editEnd.getText().toString();
            String size = editSize.getText().toString();
            String weight = editWeight.getText().toString();

            int distance = calculateDistance(start, end);
            int price = distance * 1;

            // Start MapActivity and pass the estimated price
            Intent intent = new Intent(EstimateActivity.this, MapActivity.class);

            intent.putExtra("estimated_price", price);
            startActivity(intent);
        });
    }

    private int calculateDistance(String start, String end) {

        return 1;
    }
}

