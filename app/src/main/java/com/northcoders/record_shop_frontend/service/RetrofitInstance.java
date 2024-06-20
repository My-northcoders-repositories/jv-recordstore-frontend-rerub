package com.northcoders.record_shop_frontend.service;

import android.util.Log;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static RetrofitInstance retrofitInstance = null;
    private static final String baseURL = "http://10.0.2.2:8080/api/v1/";

    public static AlbumAPIService getService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AlbumAPIService albumAPIService = retrofit.create(AlbumAPIService.class);
            return albumAPIService;
    }
}
