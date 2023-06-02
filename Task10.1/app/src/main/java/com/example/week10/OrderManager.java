package com.example.week10;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private List<Truck> selectedTrucks;

    private OrderManager() {
        selectedTrucks = new ArrayList<>();
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addTruck(Truck truck) {
        selectedTrucks.add(truck);
    }

    public List<Truck> getSelectedTrucks() {
        return selectedTrucks;
    }
}

