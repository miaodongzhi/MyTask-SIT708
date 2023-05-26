package com.example.week7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

public class CreateAdvertActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;
    private EditText locationEditText;
    private RadioButton lostRadioButton;
    //private RadioButton foundRadioButton;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found);

        itemList = (List<Item>) getIntent().getSerializableExtra("itemList");

        nameEditText = findViewById(R.id.name_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        descriptionEditText = findViewById(R.id.description_edit_text);
        dateEditText = findViewById(R.id.date_edit_text);
        locationEditText = findViewById(R.id.location_edit_text);
        lostRadioButton = findViewById(R.id.lost_radio_button);
        //foundRadioButton = findViewById(R.id.found_radio_button);

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String date = dateEditText.getText().toString();
            String location = locationEditText.getText().toString();

            boolean isLost = lostRadioButton.isChecked();

            Item item = new Item(name, phone, description, date, location, isLost);

            itemList.add(item);

            Intent intent = new Intent(CreateAdvertActivity.this, MainActivity.class);
            intent.putExtra("itemList", (Serializable) itemList);
            startActivity(intent);
            finish();
        });
    }
}





