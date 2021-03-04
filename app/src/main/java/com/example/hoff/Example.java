package com.example.hoff;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    @SerializedName("items")
    Item items;


    public Item getItems() {
        return items;
    }

    public void setItems(Item items) {
        this.items = items;
    }



}

