package com.northcoders.record_shop_frontend.ui.mainactivity.updateAlbum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.northcoders.record_shop_frontend.MainActivity;
import com.northcoders.record_shop_frontend.model.Album;
import com.northcoders.record_shop_frontend.ui.mainactivity.mainactivity.MainActivityViewModel;

import java.util.Objects;

public class UpdateAlbumClickHandler {
    Album album;
    MainActivityViewModel mainActivityViewModel;
    Long albumId;
    Context context;

    public UpdateAlbumClickHandler(Context context, MainActivityViewModel mainActivityViewModel, Album album) {
        this.context = context;
        this.mainActivityViewModel = mainActivityViewModel;
        this.album = album;
    }

    public void onSubmitButtonClicked(View view) {
        //- In the 'Submit' button method assign the attributes of the `Album` object to a new `Album` object (these attributes will be updated through data binding in the UI).
        //  If so, create a pop up message advising that the fields can not be empty.
        //  (this will pass the id as a 'path variable' to the endpoint with the new object).

        Album updatedAlbum = new Album();
        updatedAlbum.setId(album.getId());
        updatedAlbum.setAlbumTitle(album.getAlbumTitle());
        updatedAlbum.setArtist(album.getArtist());
        updatedAlbum.setGenre(album.getGenre());
        updatedAlbum.setPricePence(album.getPricePence());
        updatedAlbum.setReleaseYear(album.getReleaseYear());

        //  Create a conditional check of whether any of the attributes of the new `Album` object are an empty `String`.
        if (updatedAlbum.getId().isEmpty()
                || updatedAlbum.getAlbumTitle().isEmpty()
                || updatedAlbum.getArtist().isEmpty()
                || updatedAlbum.getGenre().isEmpty()
                || updatedAlbum.getReleaseYear().isEmpty()
                || updatedAlbum.getPricePence().isEmpty()) {
            Toast.makeText(context, "no empty fields pls", Toast.LENGTH_SHORT).show();
        }
        //  If not, get the id of the `Album` object and pass this, and the new `Album` object, to the `updateAlbum()` method from the ViewModel and start the `MainActivity`
        else {
            mainActivityViewModel.updateAlbum(updatedAlbum);
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }

    public void onDeleteButtonClicked(View view) {
        String id = album.getId();
        mainActivityViewModel.deleteAlbum(id);
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void onBackButtonClicked(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}







