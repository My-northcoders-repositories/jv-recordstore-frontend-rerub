package com.northcoders.record_shop_frontend.service;

import com.northcoders.record_shop_frontend.model.Album;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface AlbumAPIService {
    @GET("records")
    Call<List<Album>> getAlbums();

    @POST("records")
    Call<Album> createAlbum(@Body Album album);

    @PUT("records/update")
    Call<Album> updateAlbum(@Body Album album);

    @DELETE("records/delete/by?id={id}")
    Call<Album> deleteAlbumBy(@Path("id") String id);

}
