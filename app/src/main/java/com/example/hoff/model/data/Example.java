package com.example.hoff.model.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    @SerializedName("items")
   public List<Item> items;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }



}

