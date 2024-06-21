package com.northcoders.record_shop_frontend.ui.mainactivity.addalbum;

import android.app.Application;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.northcoders.record_shop_frontend.R;
import com.northcoders.record_shop_frontend.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.record_shop_frontend.databinding.AlbumItemBinding;
import com.northcoders.record_shop_frontend.model.Album;
import com.northcoders.record_shop_frontend.ui.mainactivity.mainactivity.MainActivityViewModel;

public class AddNewAlbumActivity extends AppCompatActivity {
    private Album album;
    private ActivityAddNewAlbumBinding binding;
    private AddAlbumClickHandlers clickHandlers;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_album);

        // TODO i dont really understand how this is working, and what the different bits are.
        album = new Album();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_album);

        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        clickHandlers = new AddAlbumClickHandlers(album, this, viewModel);

        binding.setAlbum(album);

        binding.setClickHandlers(clickHandlers);

    }
}