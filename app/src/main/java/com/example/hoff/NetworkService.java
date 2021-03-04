package com.example.hoff;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService INSTANCE = null;
    private Retrofit retrofit;
    String BASE_URL = "https://hoff.ru/api/v2/";

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkService();
        }
        return INSTANCE;
    }

    public RestService getJsonApi() {
        return retrofit.create(RestService.class);
    }
}
