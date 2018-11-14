package jaa.com.likeastarapp.common.network;

import jaa.com.likeastarapp.common.dao.FilmImage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface FilmImageApi {
    @GET("http://www.omdbapi.com/")
    Call<FilmImage> getFilmImage(@Query("apikey") String apikey,@Query("t") String title);
}
