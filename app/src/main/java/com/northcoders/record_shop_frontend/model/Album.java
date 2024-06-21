package com.northcoders.record_shop_frontend.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.northcoders.record_shop_frontend.BR;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
// TODO changed year to string for 2way binding, but left price as long and used explicit getpricePence rather than just price pence.
// TODO will it work?

public class Album extends BaseObservable {

    // serialised name allows you to set the attributes here to parts of a json even if they do not have matching names, not relevant here.
    @SerializedName("id")
    String id;

    String albumTitle;

    String artist;

    String genre;

    String releaseYear;

    Long pricePence;

    public Album(String id, String albumTitle, String artist, String genre, String releaseYear, Long pricePence) {
        this.id = id;
        this.albumTitle = albumTitle;
        this.artist = artist;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.pricePence = pricePence;
    }

    public Album() {
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);

    }

    @Bindable
    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {

        this.albumTitle = albumTitle;
        notifyPropertyChanged(BR.albumTitle);

    }

    @Bindable
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
        notifyPropertyChanged(BR.artist);

    }

    @Bindable
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);

    }

    @Bindable
    public String getReleaseYear() {
        return String.valueOf(releaseYear);
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
        notifyPropertyChanged(BR.releaseYear);

    }

    @Bindable
    public String getPricePence() {
        DecimalFormat decimalFormat = new DecimalFormat("£#.00");
        Long pounds = this.pricePence/100;
        return String.valueOf(decimalFormat.format(pounds));
    }

    public void setPricePence(Long pricePence) {
        this.pricePence = pricePence;
        notifyPropertyChanged(BR.pricePence);

    }
}
