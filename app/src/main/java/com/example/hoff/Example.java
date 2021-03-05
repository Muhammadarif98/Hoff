package com.example.hoff;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    @SerializedName("items")
    List<Item> items;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }



}

