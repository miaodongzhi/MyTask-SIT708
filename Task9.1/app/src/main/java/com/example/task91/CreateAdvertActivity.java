package com.example.task91;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class CreateAdvertActivity extends AppCompatActivity {

    private Spinner spinnerLostFound;
    private EditText editTextName, editTextPhone, editTextDescription, editTextDate, editTextLocation;
    private Button btnGetCurrentLocation, btnSaveAdvert;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        spinnerLostFound = findViewById(R.id.spinnerLostFound);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDate = findViewById(R.id.editTextDate);
        editTextLocation = findViewById(R.id.editTextLocation);
        btnGetCurrentLocation = findViewById(R.id.btnGetCurrentLocation);
        btnSaveAdvert = findViewById(R.id.btnSaveAdvert);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        btnGetCurrentLocation.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(CreateAdvertActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(CreateAdvertActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CreateAdvertActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return;
            }

            fusedLocationClient.getLastLocation().addOnSuccessListener(CreateAdvertActivity.this, location -> {
                if (location != null) {
                    editTextLocation.setText(location.getLatitude() + "," + location.getLongitude());
                } else {
                    Toast.makeText(CreateAdvertActivity.this, "Unable to get location", Toast.LENGTH_SHORT).show();
                }
            });
        });

        btnSaveAdvert.setOnClickListener(v -> {
            Item.ItemType type = spinnerLostFound.getSelectedItem().toString().equals("LOST") ?
                    Item.ItemType.LOST : Item.ItemType.FOUND;
            String name = editTextName.getText().toString();
            String phone = editTextPhone.getText().toString();
            String description = editTextDescription.getText().toString();
            String date = editTextDate.getText().toString();
            String location = editTextLocation.getText().toString();

            Item newItem = new Item(type, name, phone, description, date, location);

            SharedPreferences sharedPreferences = getSharedPreferences("com.example.task91", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            List<Item> itemList;
            Gson gson = new Gson();
            String json = sharedPreferences.getString("itemList", null);
            Type typeOfItemList = new TypeToken<List<Item>>(){}.getType();
            if (json == null) {
                itemList = new ArrayList<>();
            } else {
                itemList = gson.fromJson(json, typeOfItemList);
            }
            itemList.add(newItem);
            json = gson.toJson(itemList);
            editor.putString("itemList", json);
            editor.apply();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                btnGetCurrentLocation.performClick();
            } else {
                Toast.makeText(CreateAdvertActivity.this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

