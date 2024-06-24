package com.northcoders.record_shop_frontend.ui.mainactivity.addalbum;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.northcoders.record_shop_frontend.MainActivity;
import com.northcoders.record_shop_frontend.model.Album;
import com.northcoders.record_shop_frontend.ui.mainactivity.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandlers {
    private Album album;
    //private Application application; trying just context?
    private Context context;
    private MainActivityViewModel viewModel;

    public AddAlbumClickHandlers(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onCreateNewAlbumClicked(View view) {

        Log.i("1", "onCreateNewAlbumClicked: ");
        if (album.getAlbumTitle()==null
                || album.getArtist()==null
                || album.getGenre()==null
                || album.getPricePence()==null
                || album.getReleaseYear()==null) {

            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            Log.i("2", "onCreateNewAlbumClicked: ");
            Intent intent = new Intent(context, MainActivity.class);
            Log.i("3", "onCreateNewAlbumClicked: ");

            Album newAlbum = new Album();
            Log.i("4", "onCreateNewAlbumClicked: ");

            newAlbum.setAlbumTitle(album.getAlbumTitle());
            newAlbum.setArtist(album.getArtist());
            newAlbum.setGenre(album.getGenre());
            newAlbum.setPricePence(album.getPricePence());
            newAlbum.setReleaseYear(album.getReleaseYear());

            viewModel.addAlbum(newAlbum);
            context.startActivity(intent);

        }

    }

    public void goBackToMainViewClicked(View view) {

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

    }

}
