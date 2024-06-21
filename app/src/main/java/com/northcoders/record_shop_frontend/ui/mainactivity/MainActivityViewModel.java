package com.northcoders.record_shop_frontend.ui.mainactivity;

import android.app.Application;
import android.telephony.ClosedSubscriberGroupInfo;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.northcoders.record_shop_frontend.model.Album;
import com.northcoders.record_shop_frontend.model.AlbumRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    AlbumRepository albumRepository;

    public MainActivityViewModel(@NotNull Application application) {
        super(application);

        this.albumRepository = new AlbumRepository(application);
    }
    public MutableLiveData<List<Album>> getData(){

        Log.i("6", "MainActivityViewModel: ");
        return albumRepository.getMutableLiveData();
    }

    public void addAlbum(Album album){
        albumRepository.addAlbum(album);
    }
}
