package com.northcoders.record_shop_frontend.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import com.northcoders.record_shop_frontend.service.AlbumAPIService;
import com.northcoders.record_shop_frontend.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class AlbumRepository {
    private MutableLiveData<List<Album>> liveData = new MutableLiveData<>();
    private Application app;

    public AlbumRepository(Application app) {
        this.app = app;
    }

    public MutableLiveData<List<Album>> getMutableLiveData() {
        // In this method, initialise an AlbumApiService variable named albumApiService and assign it the value of the getService() method from the RetrofitInstance.
        AlbumAPIService albumAPIService = RetrofitInstance.getService();
        //Then assign the result of a method call to albumApiService.getAllAlbums() to a variable called call.
        Log.d("7", "getMutableLiveData: ");
        Call call = albumAPIService.getAlbums();
        //Now, leveraging the method enqueue available to call, pass in a new Callback<List<Album>>(){} as an argument and implement the overridden methods.
        // This will implement the methods onResponse() and onFailure().

        call.enqueue(new Callback<List<Album>>() {
            @Override
            //In here, assign a variable of a list of Album objects to the response variable being passed into the method's body.
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                List<Album> albums = response.body();
                //Then pass this variable as an argument to mutableLiveData.setValue();.
                liveData.setValue(albums);
                //Ensure your method returns this mutableLiveData and move on to the next task.*/
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable throwable) {
            }
        });
        return liveData;
    }


    public void addAlbum(Album album) {
        AlbumAPIService albumAPIService = RetrofitInstance.getService();
        Call<Album> call = albumAPIService.createAlbum(album);

        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {

                Toast.makeText(app.getApplicationContext(), "successful add", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Album> call, Throwable throwable) {

                Toast.makeText(app.getApplicationContext(), "no good", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateAlbum(Album album) {
        AlbumAPIService albumAPIService = RetrofitInstance.getService();
        Call<Album> call = albumAPIService.updateAlbum(album);
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Toast.makeText(app.getApplicationContext(), "successful edit", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Album> call, Throwable throwable) {
                Toast.makeText(app.getApplicationContext(), "no bueno", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void deleteAlbum(String id) {
        AlbumAPIService albumAPIService = RetrofitInstance.getService();
        Call<Album> call = albumAPIService.deleteAlbumBy(id);
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                Toast.makeText(app.getApplicationContext(), "successful delete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Album> call, Throwable throwable) {
                Toast.makeText(app.getApplicationContext(), "no bueno", Toast.LENGTH_SHORT).show();

            }
        });
    }

}





