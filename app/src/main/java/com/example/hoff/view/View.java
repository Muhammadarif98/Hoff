package com.example.hoff.view;

import com.example.hoff.model.data.Item;

import java.util.List;

public interface View {

    void showData(List<Item> list);

    void showError(String error);
    boolean showProgress();
    boolean hideProgress();

}
