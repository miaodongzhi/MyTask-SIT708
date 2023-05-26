package com.example.week7;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private String phone;
    private String description;
    private String date;
    private String location;
    private boolean isLost;

    public Item(String name, String phone, String description, String date, String location, boolean isLost) {
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.isLost = isLost;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public boolean isLost() {
        return isLost;
    }

    @Override
    public String toString() {
        return name + " - " + (isLost ? "Lost" : "Found");
    }
}


