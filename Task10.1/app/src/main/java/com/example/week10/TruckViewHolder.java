package com.example.week10;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TruckViewHolder extends RecyclerView.ViewHolder {
    ImageView truckImage;
    TextView truckName;
    TextView truckDescription;

    public TruckViewHolder(View itemView) {
        super(itemView);
        truckImage = itemView.findViewById(R.id.truck_image);
        truckName = itemView.findViewById(R.id.truck_name);
        truckDescription = itemView.findViewById(R.id.truck_description);
    }
}


