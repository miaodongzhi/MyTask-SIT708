package com.example.week10;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TruckAdapter extends RecyclerView.Adapter<TruckAdapter.TruckViewHolder> {
    private List<Truck> truckList;
    private Context context;

    public TruckAdapter(List<Truck> truckList, Context context) {
        this.truckList = truckList;
        this.context = context;
    }

    @NonNull
    @Override
    public TruckAdapter.TruckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_truck, parent, false);
        return new TruckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruckAdapter.TruckViewHolder holder, int position) {
        Truck truck = truckList.get(position);
        holder.truckName.setText(truck.getName());
        holder.truckDescription.setText(truck.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EstimateActivity.class);
            intent.putExtra("selected_truck", truck);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return truckList.size();
    }

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
}


