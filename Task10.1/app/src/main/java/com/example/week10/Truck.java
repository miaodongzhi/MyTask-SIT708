package com.example.week10;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Truck implements Serializable {
    private String name;
    private String description;
    private String imageUrl;

    public Truck(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}



