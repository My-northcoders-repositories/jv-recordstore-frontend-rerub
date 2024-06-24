package com.northcoders.record_shop_frontend;

import android.content.Intent;
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
import com.northcoders.record_shop_frontend.ui.mainactivity.mainactivity.RecyclerViewInterface;
import com.northcoders.record_shop_frontend.ui.mainactivity.updateAlbum.UpdateAlbumActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

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
        getAllAlbums();
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

        String sortedByString = spinner.getSelectedItem().toString();
        Log.i(sortedByString, "onCreate: ");
        String upper = (sortedByString.toUpperCase());
        Log.i(upper, "onCreate: ");
        String cleaned = cleanSortedBy(upper);
        Log.i(cleaned, "Cleaned ");
        //sortAlbums(cleaned);


    }

    private void getAllAlbums() {
        Log.i("5", "getAllAlbums: ");
        mainActivityViewModel.getData().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                albumList = new ArrayList<>(albumsFromLiveData);
                displayInRecyclerView();
            }
        });
    }

    public void sortAlbums(String sortedBy) {
        Log.i(sortedBy, "sortAlbums: ");
        switch (sortedBy) {
            case "TITLE":
                albumList.stream().sorted();
                Log.i("title", "sortAlbums: ");
                displayInRecyclerView();
            case "PRICE":
                albumList.stream().sorted(Comparator.comparingInt(album -> Integer.parseInt(album.getPricePence())));
                Log.i("price", "sortAlbums: ");
                displayInRecyclerView();
            case "DATE":
                albumList =  albumList.stream().sorted(Comparator.comparingInt(album -> Integer.parseInt(album.getReleaseYear()))).collect(Collectors.toList());
                Log.i("date", "sortAlbums: ");
                displayInRecyclerView();
        }
    }


    private String cleanSortedBy(String sortedByString) {
        Log.d("2", "cleanSortedBy: ");
        if (sortedByString.contains("TITLE")) {
            return "TITLE";
        } else if (sortedByString.contains("DATE")) {
            return "DATE";
        } else return "not working";
    }


    private void displayInRecyclerView() {
        Log.i("1", "displayInRecyclerView: ");
        recyclerView = activityMainBinding.recyclerView;
        albumAdaptor = new AlbumAdaptor(albumList, this, this);
        recyclerView.setAdapter(albumAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        albumAdaptor.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(int position) {
        Log.i("129", "onItemClick: ");
        Intent intent = new Intent(this, UpdateAlbumActivity.class);
        Log.i("beofre album key bit?", "onItemClick: ");
        intent.putExtra("ALBUM_KEY", albumList.get(position));
        Log.d("just before start activity", "onItemClick: ");
        startActivity(intent);

    }
}

