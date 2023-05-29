package com.example.task91;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShowOnMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_on_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.task91", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("itemList", null);
        Type typeOfItemList = new TypeToken<List<Item>>(){}.getType();
        itemList = gson.fromJson(json, typeOfItemList);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Sydney = new LatLng(-34,151);
        mMap.addMarker(new MarkerOptions().position(Sydney).title("Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Sydney));


        //I do not know why I go to US...haha
        for (Item item : itemList) {
            String[] latLngStr = item.getLocation().split(",");
            double latitude = Double.parseDouble(latLngStr[0]);
            double longitude = Double.parseDouble(latLngStr[1]);
            LatLng location = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(location).title(item.getName()));
        }

        if (!itemList.isEmpty()) {
            String[] latLngStr = itemList.get(0).getLocation().split(",");
            double latitude = Double.parseDouble(latLngStr[0]);
            double longitude = Double.parseDouble(latLngStr[1]);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));
        }
    }
}


