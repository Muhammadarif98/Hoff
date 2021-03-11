package com.example.hoff.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hoff.model.data.Item;
import com.example.hoff.presenter.IPresenter;
import com.example.hoff.presenter.Presenter;
import com.example.hoff.view.adapters.MyAdapter;
import com.example.hoff.R;

import java.util.List;

public class CatalogActivity extends AppCompatActivity implements View {
    private static final int LIMIT = 40;
    private int page = 0;
    private int categoryId = 320;
    private String sort_by = "popular";
    private String sort_type = "desc";

    private static final boolean isNotMoreData = false;

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private IPresenter mIpresenter;
    SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        final String str[] = {"Сначала дешевые", "Сначала дорогие", "Популярные товары", "По скидкам"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(CatalogActivity.this, android.R.layout.simple_dropdown_item_1line, str);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mIpresenter = new Presenter(this);

        mRecyclerView = findViewById(R.id.recyclerViews);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        myAdapter = new MyAdapter(mPrefs);
        mRecyclerView.setAdapter(myAdapter);

        mIpresenter.loadData(page);

    }

    @Override
    public void showData(List<Item> list) {
        myAdapter.addItems(list);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mIpresenter != null) {
            mIpresenter.onStop();
        }
    }

}