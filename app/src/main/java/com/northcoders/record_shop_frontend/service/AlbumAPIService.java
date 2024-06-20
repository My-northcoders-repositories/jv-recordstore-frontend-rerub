package com.northcoders.record_shop_frontend.service;

import com.northcoders.record_shop_frontend.model.Album;
import retrofit2.http.GET;

import java.util.List;

public interface AlbumAPIService {
    // This may be wrong - check retrofit docs if not working
    @GET("records")
    retrofit2.Call<List<Album>> getAlbums();


}
