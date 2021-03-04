package com.example.hoff;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private MyHolder mHolder;
    SharedPreferences mPrefs ;
    private ArrayList<Example> mTovars =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        final String str[] = {"Сначала дешевые","Сначала дорогие","Популярные товары","По скидкам"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(CatalogActivity.this, android.R.layout.simple_dropdown_item_1line, str);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mRecyclerView = findViewById(R.id.recyclerViews);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);


        NetworkService.getInstance()
                .getJsonApi()
                .getItem()
                .enqueue(new Callback<List<Example>>() {
                    @Override
                    public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mTovars = new ArrayList<>(response.body());
                            myAdapter = new MyAdapter(mPrefs, mTovars);
                            mRecyclerView.setAdapter(myAdapter);

                        }
                        Log.d("TAG2", "onResponse" + (response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<Example>> call, Throwable throwable) {
                        Log.d("TAG", "Response Failure =" + throwable.toString());
                        Toast.makeText(CatalogActivity.this,"Упс! Что то пошло не так", Toast.LENGTH_SHORT).show();

                    }


                });


    }
}