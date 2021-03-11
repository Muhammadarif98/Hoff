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
import com.example.hoff.model.data.Example;

import java.util.List;

public class CatalogActivity extends AppCompatActivity implements View{
    private static final int LIMIT = 40;
    private int page = 0;
    private int categoryId = 320;
    private String sort_by = "popular";
    private String sort_type="desc";

    private static final boolean isNotMoreData = false;

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private IPresenter mIpresenter;
    SharedPreferences mPrefs ;
    private Example mTovars;
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
        mIpresenter = new Presenter(this);

        mRecyclerView = findViewById(R.id.recyclerViews);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mPrefs = PreferenceManager
                .getDefaultSharedPreferences(this);

        myAdapter = new MyAdapter(mPrefs);
        mRecyclerView.setAdapter(myAdapter);

      mIpresenter.loadData();

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
        if (mIpresenter!= null) {
            mIpresenter.onStop();
        }
    }


//    public void fetchData(){
//        NetworkService.getInstance()
//                .getJsonApi()
//                .getItem(LIMIT, LIMIT * page,categoryId,sort_by,sort_type)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<Response<Example>>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(@NonNull Response<Example> response) {
//                        if (response.isSuccessful() && response.body() != null) {
//                            Example body = response.body();
//                            myAdapter.addItems(body.items);
//                            mRecyclerView.setAdapter(myAdapter);
//                        }
//                        Log.d("TAG1", "onNext" + (response.body()));
//                    }
//
////                    @Override
////                    public void onNext(@NonNull Response<Example> response) {
////                        if (response.isSuccessful() && response.body() != null) {
////                            Example body = response.body();
////                            myAdapter.addItems(body.items);
////                            mRecyclerView.setAdapter(myAdapter);
////                        }
////                        Log.d("TAG1", "onNext" + (response.body()));
////                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.d("TAG", "onError =" + e.toString());
//                        Toast.makeText(CatalogActivity.this,"Упс! Что то пошло не так", Toast.LENGTH_SHORT).show();
//                    }
//
////                    @Override
////                    public void onComplete() {
////
////                    }
//                });
//    }
//    public void loadData(int page){
//        NetworkService.getInstance()
//                .getJsonApi()
//                .getItem(LIMIT, LIMIT * page,categoryId,sort_by,sort_type)
//                .enqueue(new Callback<Example>() {
//                    @Override
//                    public void onResponse(Call<Example> call, Response<Example> response) {
//                        if (response.isSuccessful() && response.body() != null) {
//                            Example body = response.body();
//                            myAdapter.addItems(body.items);
//                            mRecyclerView.setAdapter(myAdapter);
//                        }
//                        Log.d("TAG2", "onResponse" + (response.body()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<Example> call, Throwable throwable) {
//                        Log.d("TAG", "Response Failure =" + throwable.toString());
//                        Toast.makeText(CatalogActivity.this,"Упс! Что то пошло не так", Toast.LENGTH_SHORT).show();
//
//                    }
//
//
//                });
//
//    }
}