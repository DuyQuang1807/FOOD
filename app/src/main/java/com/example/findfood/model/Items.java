package com.example.findfood.model;

public class Items {
    private String description;
    private String imageurl;

    public Items() {
    }

    public Items(String description, String imageurl) {
        this.description = description;
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
