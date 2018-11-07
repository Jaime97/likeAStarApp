package jaa.com.likeastarapp.common.network;

import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmLocationsApi {
    @GET("resource/wwmu-gmzc.json")
    Call<List<Film>> getFilmList();
}
