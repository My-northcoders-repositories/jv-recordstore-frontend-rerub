package com.northcoders.record_shop_frontend.ui.mainactivity.updateAlbum;

import android.os.Build;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.northcoders.record_shop_frontend.R;
import com.northcoders.record_shop_frontend.databinding.ActivityEditAlbumBinding;
import com.northcoders.record_shop_frontend.model.Album;
import com.northcoders.record_shop_frontend.ui.mainactivity.mainactivity.MainActivityViewModel;

public class UpdateAlbumActivity extends AppCompatActivity {

ActivityEditAlbumBinding activityEditAlbumBinding;
UpdateAlbumClickHandler updateAlbumClickHandler;
Album album;
private static final String ALBUM_KEY = "ALBUM_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_album);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            activityEditAlbumBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_album);
            album = getIntent().getParcelableExtra(ALBUM_KEY, Album.class);
            activityEditAlbumBinding.setAlbum(album);
            MainActivityViewModel viewModel =  new ViewModelProvider(this).get(MainActivityViewModel.class);
            updateAlbumClickHandler = new UpdateAlbumClickHandler(this, viewModel, album);
            activityEditAlbumBinding.setClickHandlers((updateAlbumClickHandler));

        }

    }


}