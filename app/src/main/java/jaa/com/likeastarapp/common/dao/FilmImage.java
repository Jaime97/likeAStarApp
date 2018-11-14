package jaa.com.likeastarapp.common.dao;

import com.google.gson.annotations.SerializedName;

public class FilmImage {

    @SerializedName("Poster")
    String poster;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
