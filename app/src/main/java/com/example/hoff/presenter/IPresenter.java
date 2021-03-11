package com.example.hoff.presenter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hoff.view.adapters.MyAdapter;

public interface IPresenter {

    void loadData();

    void onStop();
}
