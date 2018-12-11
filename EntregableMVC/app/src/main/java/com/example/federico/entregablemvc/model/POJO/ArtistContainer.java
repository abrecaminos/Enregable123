package com.example.federico.entregablemvc.model.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistContainer {

    @SerializedName("artists")
    @Expose
    private List<Artist_> artists = null;

    public List<Artist_> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist_> artists) {
        this.artists = artists;
    }

}
