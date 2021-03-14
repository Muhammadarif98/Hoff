package com.example.hoff.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.hoff.model.data.Item;
import com.example.hoff.presenter.IPresenter;
import com.example.hoff.presenter.Presenter;
import com.example.hoff.view.adapters.MyAdapter;
import com.example.hoff.R;

import java.util.List;

public class CatalogActivity extends AppCompatActivity implements View {
    private static final int LIMIT = 20;
    private static final int PAGE_START = 0;
    private int page = PAGE_START;

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private IPresenter mIpresenter;
    SharedPreferences mPrefs;
    private ProgressBar mProgressBar;
    NestedScrollView mNestedScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        final String str[] = {"Сначала дешевые", "Сначала дорогие", "Популярные товары", "По скидкам"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(CatalogActivity.this, android.R.layout.simple_dropdown_item_1line, str);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);

        mProgressBar = findViewById(R.id.progress);
        mNestedScrollView = findViewById(R.id.nestedScroll);

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
        mIpresenter.loadData(page, LIMIT);
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    page = page + LIMIT;
                    showProgress();
                    mIpresenter.loadData(page, LIMIT);
                }
            }
        });

    }


    @Override
    public void showData(List<Item> list) {
        myAdapter.addItems(list);

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public boolean showProgress() {
        mProgressBar.setVisibility(android.view.View.VISIBLE);
        return true;
    }

    @Override
    public boolean hideProgress() {
        mProgressBar.setVisibility(android.view.View.GONE);
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mIpresenter != null) {
            mIpresenter.onStop();
        }
    }

}