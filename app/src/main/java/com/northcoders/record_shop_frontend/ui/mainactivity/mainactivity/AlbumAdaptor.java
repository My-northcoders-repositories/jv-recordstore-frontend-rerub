package com.northcoders.record_shop_frontend.ui.mainactivity.mainactivity;

import android.content.Context;
import android.telephony.ClosedSubscriberGroupInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.northcoders.record_shop_frontend.R;
import com.northcoders.record_shop_frontend.databinding.AlbumItemBinding;
import com.northcoders.record_shop_frontend.model.Album;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumAdaptor extends RecyclerView.Adapter<AlbumAdaptor.AlbumViewHolder> {

    private List<Album> albumList;
    private Context context;
    private RecyclerViewInterface recyclerViewInterface;


    public AlbumAdaptor(List<Album> albums, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.albumList = albums;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @NotNull
    @Override
    // onCreateViewHolder: creates new ViewHolders for the items and deals with the inflation of the card layout as an item for the RecyclerView.
    public AlbumViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                // connection to the xml layout, false means it is not attached to the viewgroup
                .inflate(R.layout.album_item, viewGroup, false);

        // TODO no idea what is going on here....
        return new AlbumViewHolder(AlbumItemBinding.bind(view), recyclerViewInterface);
    }

    // onBindViewHolder: which deals with the setting of different data and methods related to particular items of the RecyclerView. In this method get the album object, which is at the passed in position, and give this as an argument to the setAlbum() method of the holder.albumItemBinding.
    @Override
    public void onBindViewHolder(@NonNull @NotNull AlbumViewHolder albumViewHolder, int i) {
        Album album = albumList.get(i);
        albumViewHolder.binding.setAlbum(album);
        albumViewHolder.bind(album);
        albumViewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return albumList.size();

    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        private AlbumItemBinding binding;
        ImageView albumCover;
        FloatingActionButton editButton;


        public AlbumViewHolder(AlbumItemBinding binding, RecyclerViewInterface recyclerViewInterface) {
            super(binding.getRoot());

            this.binding = binding;
            albumCover = itemView.findViewById(R.id.albumImage);
            editButton =  itemView.findViewById(R.id.editButton);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("1", "onClickkk: ");
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    } else {
                        Log.d("2", "onClick: recyclerview is null, or has no position?");
                    }
                }
            });
        }


        static Map<String, Integer> albumCoverMap = new HashMap<>();

        static {
            albumCoverMap.put("ATLIENS", R.drawable.atliens);
            albumCoverMap.put("AQUEMINI", R.drawable.aquemini);
            albumCoverMap.put("HARVESTTIME", R.drawable.harvesttime);
            albumCoverMap.put("KINDOFBLUE", R.drawable.kindofblue);
            albumCoverMap.put("NEWFORMS", R.drawable.newforms);
            albumCoverMap.put("THECHRONIC", R.drawable.thechronic);
            albumCoverMap.put("PETERANDTHEWOLF", R.drawable.peterandthewolf);
            albumCoverMap.put("TOXICITY", R.drawable.toxicity);
            albumCoverMap.put("UNTRUE", R.drawable.untrue);
            albumCoverMap.put("SELECTEDAMBIENTWORKS85-92", R.drawable.selectedambientworks);

        }

        public void bind(Album album) {
            String albumTitle = album
                    .getAlbumTitle()
                    .toUpperCase()
                    .replace(" ", "");
            Integer drawableResourceId = albumCoverMap.get(albumTitle);

            if (drawableResourceId != null) {
                binding.albumImage.setImageResource(drawableResourceId);
            } else {
                binding.albumImage.setImageResource(R.drawable.vinyl);
            }
        }


    }
}
