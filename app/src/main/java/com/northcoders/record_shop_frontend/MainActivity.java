package com.northcoders.record_shop_frontend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.SearchView.OnQueryTextListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
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
    private SearchView searchView;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialise binding variable
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // initialise veiw model
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        activityMainBinding.setLifecycleOwner(this);
        getAllAlbums();
        // Initialize ClickHandler
        clickHandler = new MainActivityClickHandler(mainActivityViewModel, this);
        // Bind ClickHandler to layout
        activityMainBinding.setClickHandler(clickHandler);

        // Initialise spinner
        //create an ArrayAdapter using the string array and default spinner layout
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sort_options, android.R.layout.simple_spinner_item);
        //specify the layout to use
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        // apply the adapter to the spinner
        spinner.setAdapter(adapter);
        // set spinner listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sortedByString = spinner.getSelectedItem().toString();
                Log.i(sortedByString, "onCreate: ");
                String upper = (sortedByString.toUpperCase());
                Log.i(upper, "onCreate: ");
                String cleaned = cleanSortedBy(upper);
                Log.i(cleaned, "Cleaned ");
                sortAlbums(cleaned);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
//                if(query == ""){
//                    displayInRecyclerView(albumList);
//                }else {
//                    List<Album> filteredAlbums = new ArrayList<>();
//                    filteredAlbums = albumList.stream()
//                            .filter(a -> a.getAlbumTitle().toLowerCase().contains(query.toLowerCase()))
//                            .collect(Collectors.toList());
//                    displayInRecyclerView(filteredAlbums);
//                }
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText == ""){
                    displayInRecyclerView(albumList);
                }else {
                    List<Album> filteredAlbums = new ArrayList<>();
                    filteredAlbums = albumList.stream()
                            .filter(a -> a.getAlbumTitle().toLowerCase().contains(newText.toLowerCase()))
                            .collect(Collectors.toList());
                    displayInRecyclerView(filteredAlbums);

                    Toast.makeText(getApplicationContext(), "Nothing found with that title", Toast.LENGTH_SHORT).show();
                }
                    return false;

            }
        });


    }


    private void getAllAlbums() {
        Log.i("5", "getAllAlbums: ");
        mainActivityViewModel.getData().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                albumList = new ArrayList<>(albumsFromLiveData);
                displayInRecyclerView(albumList);
            }
        });
    }

    public void sortAlbums(String sortedBy) {
        Log.i(sortedBy, "sortAlbums: ");
        switch (sortedBy) {
            case "TITLE":
                albumList = albumList.stream()
                        .sorted(Comparator.comparing(Album::getAlbumTitle))
                        .collect(Collectors.toList());
                break;
            case "ARTIST":
                albumList = albumList.stream()
                        .sorted(Comparator.comparing(Album::getArtist))
                        .collect(Collectors.toList());
                break;
            case "PRICE":
                albumList = albumList.stream().sorted(Comparator.comparingInt(album -> Integer.parseInt(album.getPricePence()))).collect(Collectors.toList());
                break;
            case "DATE":
                albumList = albumList.stream().sorted(Comparator.comparingInt(album -> Integer.parseInt(album.getReleaseYear()))).collect(Collectors.toList());
                Log.i("date", "sortAlbums: ");
                break;

        }
        displayInRecyclerView(albumList);

    }


    private String cleanSortedBy(String sortedByString) {
        Log.d("2", "cleanSortedBy: ");
        if (sortedByString.contains("TITLE")) {
            return "TITLE";
        } else if (sortedByString.contains("DATE")) {
            return "DATE";
        } else if (sortedByString.contains("ARTIST")) {
            return "ARTIST";
        } else if (sortedByString.contains("PRICE")) {
            return "PRICE";
        } else return "not working";
    }


    private void displayInRecyclerView(List<Album> albums) {
        Log.i("1", "displayInRecyclerView: ");
        recyclerView = activityMainBinding.recyclerView;
        albumAdaptor = new AlbumAdaptor(albums, this, this);
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

