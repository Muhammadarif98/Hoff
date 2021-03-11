package com.example.hoff.presenter;


import android.util.Log;

import com.example.hoff.model.Model;
import com.example.hoff.model.ModelImpl;
import com.example.hoff.model.data.Example;
import com.example.hoff.view.View;

import io.reactivex.disposables.Disposable;

public class Presenter implements IPresenter {
    private static final int LIMIT = 40;
    private int page = 0;
    private int categoryId = 320;
    private String sort_by = "popular";
    private String sort_type = "desc";
    private Model model = new ModelImpl();

    private View mView;
    private Disposable mDisposable;

    public Presenter(View view) {
        mView = view;
    }

    @Override
    public void loadData(int page) {
        mDisposable = model.getExample(LIMIT, page, categoryId, sort_by, sort_type)
                .subscribe(response -> {
                    if (response.isSuccessful() && response.body() != null) {
                        Example body = response.body();
                        mView.showData(body.items);
                    }
                }, e -> {
                    mView.showError("Упс! Что то пошло не так");
                    Log.d("TAG", "onError =" + e.toString());
                });
    }

    @Override
    public void onStop() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
