package com.northcoders.record_shop_frontend.service;

import com.northcoders.record_shop_frontend.model.Album;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface AlbumAPIService {
    @GET("records")
    Call<List<Album>> getAlbums();

    @POST("records")
    Call<Album> createAlbum(@Body Album album);

}
