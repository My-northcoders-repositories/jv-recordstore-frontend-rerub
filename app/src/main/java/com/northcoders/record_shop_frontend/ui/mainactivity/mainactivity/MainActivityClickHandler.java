package com.northcoders.record_shop_frontend.ui.mainactivity.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.northcoders.record_shop_frontend.ui.mainactivity.addalbum.AddNewAlbumActivity;

public class MainActivityClickHandler {
private MainActivityViewModel viewModel;
// can i just do context rather than application get context?
private Context context;

    public MainActivityClickHandler(MainActivityViewModel viewModel, Context context) {
        this.viewModel = viewModel;
        this.context = context;
    }

    public void goToAddAlbumClicked(View view){
        Log.i("a", "goToAddAlbumClicked: ");
        Intent intent = new Intent(context, AddNewAlbumActivity.class);
        context.startActivity(intent);

    }

}
