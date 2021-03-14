package com.example.hoff.model;

import com.example.hoff.model.data.Example;

import io.reactivex.Single;
import retrofit2.Response;


public interface Model {
    Single<Response<Example>> getExample( int limit, int offset, int category_id, String popular, String desc);
}
