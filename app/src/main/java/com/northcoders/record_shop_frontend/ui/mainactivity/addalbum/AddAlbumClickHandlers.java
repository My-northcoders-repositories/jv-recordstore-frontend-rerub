package com.northcoders.record_shop_frontend.ui.mainactivity.addalbum;

import android.app.Application;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.northcoders.record_shop_frontend.MainActivity;
import com.northcoders.record_shop_frontend.model.Album;
import com.northcoders.record_shop_frontend.ui.mainactivity.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandlers {
    private Album album;
    private Application application;
    private MainActivityViewModel viewModel;

    public AddAlbumClickHandlers(Album album, Application application, MainActivityViewModel viewModel) {
        this.album = album;
        this.application = application;
        this.viewModel = viewModel;
    }

    public void onCreateNewAlbumClicked(View view) {
        if (album.getAlbumTitle().isEmpty()
                || album.getArtist().isEmpty()
                || album.getGenre().isEmpty()
                || album.getPricePence().isEmpty()
                || album.getReleaseYear().isEmpty()) {
            Toast.makeText(application.getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }else{

            Intent intent = new Intent(application.getApplicationContext(), MainActivity.class);

            Album newAlbum = new Album();
            newAlbum.setAlbumTitle(album.getAlbumTitle());
            newAlbum.setArtist(album.getArtist());
            newAlbum.setGenre(album.getGenre());
            newAlbum.setPricePence(album.getPricePence());
            newAlbum.setReleaseYear(album.getReleaseYear());

            viewModel.addAlbum(newAlbum);
            application.getApplicationContext().startActivity(intent);

        }

    }

    public void goBackToMainViewClicked(View view) {

        Intent intent = new Intent(application.getApplicationContext(), MainActivity.class);
        application.getApplicationContext().startActivity(intent);

    }

}
