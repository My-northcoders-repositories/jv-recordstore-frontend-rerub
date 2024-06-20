package com.northcoders.record_shop_frontend.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.northcoders.record_shop_frontend.R;
import com.northcoders.record_shop_frontend.databinding.AlbumItemBinding;
import com.northcoders.record_shop_frontend.model.Album;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AlbumAdaptor extends RecyclerView.Adapter<AlbumAdaptor.AlbumViewHolder> {

    private List<Album> albumList;
    private Context context;


    public AlbumAdaptor(List<Album> albums, Context context) {
        this.albumList = albums;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                // connection to the xml layout, false means it is not attached to the viewgroup
                .inflate(R.layout.album_item, viewGroup, false);

        // TODO no idea what is going on here....
        return new AlbumViewHolder(AlbumItemBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AlbumViewHolder albumViewHolder, int i) {
        Album album = albumList.get(i);
        albumViewHolder.binding.setAlbum(album);
        albumViewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    /*onCreateViewHolder: creates new ViewHolders for the items and deals with the inflation of the card layout as an item for the RecyclerView.
onBindViewHolder: which deals with the setting of different data and methods related to particular items of the RecyclerView. In this method get the album object, which is at the passed in position, and give this as an argument to the setAlbum() method of the holder.albumItemBinding.
getItemCount: this needs to return the length of the RecyclerView in relation to the size of the list of data being displayed.
*/


    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        private AlbumItemBinding binding;

        /*ImageView mishkin;
        ImageView cat;
        TextView albumTitle;
        TextView artist;
        TextView genre;
        TextView releaseYear;
        TextView price;
*/
        public AlbumViewHolder(AlbumItemBinding binding) {
//            super(itemView);
            super(binding.getRoot());
            this.binding = binding;
            /*mishkin = itemView.findViewById(R.id.mishkin);
            cat = itemView.findViewById(R.id.cat);
            albumTitle = itemView.findViewById(R.id.albumTitle);
            artist = itemView.findViewById(R.id.albumArtist);
            genre = itemView.findViewById(R.id.albumGenre);
            releaseYear = itemView.findViewById(R.id.albumReleaseYear;
            price = itemView.findViewById(R.id.albumPrice);*/
        }

    }
}
