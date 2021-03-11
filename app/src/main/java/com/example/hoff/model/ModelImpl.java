package com.example.hoff.model;

import com.example.hoff.model.data.Example;
import com.example.hoff.model.network.NetworkService;
import com.example.hoff.model.network.RestService;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ModelImpl implements Model{

    RestService mRestService =  NetworkService.getInstance().getJsonApi();
    @Override
    public Single<Response<Example>> getExample(int limit, int offset, int category_id, String popular, String desc) {
        return mRestService.getItem(limit,offset,category_id,popular,desc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
