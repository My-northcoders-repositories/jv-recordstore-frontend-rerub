package com.northcoders.record_shop_frontend;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.northcoders.record_shop_frontend.databinding.ActivityMainBinding;
import com.northcoders.record_shop_frontend.ui.mainactivity.mainactivity.AlbumAdaptor;
import com.northcoders.record_shop_frontend.ui.mainactivity.mainactivity.MainActivityClickHandler;
import com.northcoders.record_shop_frontend.ui.mainactivity.mainactivity.MainActivityViewModel;
import com.northcoders.record_shop_frontend.model.Album;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Album> albumList;
    private AlbumAdaptor albumAdaptor;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler clickHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialise binding variable
        Log.i("1", "1111111111111: ");
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // initialise veiw model
        Log.i("2", "22222222222");
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        Log.i("3", "33333333333333");
        activityMainBinding.setLifecycleOwner(this);
        Log.i("4", "444444444444");
        getAllAlbums("none");
        // Initialize ClickHandler
        clickHandler = new MainActivityClickHandler(mainActivityViewModel, this);
        // Bind ClickHandler to layout
        activityMainBinding.setClickHandler(clickHandler);

        // Initialise spinner
        //create an ArrayAdapter using the string array and default spinner layout
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sort_options, android.R.layout.simple_spinner_item);
        //specify the layout to use
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // apply the adapter to the spinner
        spinner.setAdapter(adapter);
        Object sortedBy = spinner.getSelectedItem();

    }

    private void getAllAlbums(String sortedBy) {
        Log.i("5", "getAllAlbums: ");
        mainActivityViewModel.getData().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                albumList = new ArrayList<>(albumsFromLiveData);

                displayInRecyclerView();
            }
        });


    }

    private void displayInRecyclerView() {
        recyclerView = activityMainBinding.recyclerView;
        albumAdaptor = new AlbumAdaptor(albumList, this);
        recyclerView.setAdapter(albumAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        albumAdaptor.notifyDataSetChanged();
    }


}

