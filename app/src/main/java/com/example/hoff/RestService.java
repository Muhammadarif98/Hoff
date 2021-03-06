package com.example.hoff;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {
    @GET("get_products_new?device_id=3a7612bcc84813b5&isAndroid=true&app_version=1.8.16&location=19&xhoff=36f40b3d665cdb9435904796172dde5e3f13aa8a%3A4630")
    Call<Example> getItem(@Query("limit") int limit,
                          @Query("offset") int offset,
                          @Query("category_id") int category_id,
                          @Query("sort_by") String popular,
                          @Query("sort_type") String desc);
}
