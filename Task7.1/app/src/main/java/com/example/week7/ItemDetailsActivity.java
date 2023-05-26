package com.example.week7;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailsActivity extends AppCompatActivity {

    private TextView nameTextView, phoneTextView, descriptionTextView, dateTextView, locationTextView, lostFoundTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        nameTextView = findViewById(R.id.name_text_view);
        phoneTextView = findViewById(R.id.phone_text_view);
        descriptionTextView = findViewById(R.id.description_text_view);
        dateTextView = findViewById(R.id.date_text_view);
        locationTextView = findViewById(R.id.location_text_view);
        lostFoundTextView = findViewById(R.id.lost_found_text_view);

        // Get the selected item from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("item")) {
            Item item = (Item) intent.getSerializableExtra("item");
            if (item != null) {
                displayItemDetails(item);
            }
        }
    }

    private void displayItemDetails(Item item) {
        nameTextView.setText(item.getName());
        phoneTextView.setText("Phone: " + item.getPhone());
        descriptionTextView.setText("Description: " + item.getDescription());
        dateTextView.setText("Date: " + item.getDate());
        locationTextView.setText("Location: " + item.getLocation());
        lostFoundTextView.setText(item.isLost() ? "Lost" : "Found");
    }
}

