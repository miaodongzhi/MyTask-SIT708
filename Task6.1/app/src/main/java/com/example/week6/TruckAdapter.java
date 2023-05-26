package com.example.week6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// TruckAdapter.java
public class TruckAdapter extends RecyclerView.Adapter<TruckAdapter.TruckViewHolder> {

    private List<Truck> truckList;

    public TruckAdapter(List<Truck> truckList) {
        this.truckList = truckList;
    }

    @NonNull
    @Override
    public TruckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_truck, parent, false);
        return new TruckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruckViewHolder holder, int position) {
        Truck truck = truckList.get(position);
        holder.txtName.setText(truck.getName());
        holder.txtType.setText(truck.getType());
    }

    @Override
    public int getItemCount() {
        return truckList.size();
    }

    public static class TruckViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtType;

        public TruckViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtTruckName);
            txtType = itemView.findViewById(R.id.txtTruckType);
        }
    }
}

