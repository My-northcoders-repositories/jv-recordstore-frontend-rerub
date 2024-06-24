package com.northcoders.record_shop_frontend.ui.mainactivity.updateAlbum;

import android.content.Context;
import android.content.Intent;
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
        }
    }

    public void onDeleteButtonClicked(View view) {
        //- The 'Delete' button logic will need to get the album id and pass this to the delete method from the view model, and also start the `MainActivity` to switch back to this view.
        String id = album.getId();

        // The id will be passed to the delete method in the ViewModel and be used in the DELETE Retrofit method, passing the id as a path variable to the backend API.
        mainActivityViewModel.deleteAlbum(id);
        // TODO this maybe needs to be a long and might have to be changed in the repostiory part
    }

    public void onBackButtonClicked(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}







