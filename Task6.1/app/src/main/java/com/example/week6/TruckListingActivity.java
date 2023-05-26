package com.example.week6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// TruckListingActivity.java
public class TruckListingActivity extends AppCompatActivity {

    private RecyclerView rvTruckList;
    private TruckAdapter truckAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truck_listing_activity);

        rvTruckList = findViewById(R.id.rvTruckList);
        rvTruckList.setLayoutManager(new LinearLayoutManager(this));

        // Create dummy truck data
        List<Truck> truckList = new ArrayList<>();
        truckList.add(new Truck("Truck 1", "Type 1"));
        truckList.add(new Truck("Truck 2", "Type 2"));
        truckList.add(new Truck("Truck 3", "Type 3"));

        truckAdapter = new TruckAdapter(truckList);
        rvTruckList.setAdapter(truckAdapter);
    }
}

