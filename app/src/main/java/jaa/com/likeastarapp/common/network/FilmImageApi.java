package jaa.com.likeastarapp.common.network;

import jaa.com.likeastarapp.common.dao.FilmImage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface FilmImageApi {
    @GET("?apikey=ee8f32ba&t={title}")
    Call<FilmImage> getFilmImage(@Path("title") String title);
}
