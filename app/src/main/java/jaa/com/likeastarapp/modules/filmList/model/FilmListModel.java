package jaa.com.likeastarapp.modules.filmList.model;

import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.network.FilmLocationsApi;
import jaa.com.likeastarapp.common.network.FilmService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FilmListModel implements FilmListModelInterface {

    private FilmListModelOutput output;
    private static Retrofit retrofit = null;
    private FilmLocationsApi service;

    public FilmListModel(FilmListModelOutput output) {
        this.output = output;
        service = FilmService.getRetrofitInstance().create(FilmLocationsApi.class);
    }

    @Override
    public void getFilmList() {
        Call<List<Film>> call = service.getFilmList();
        call.enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                List<Film> filmList = response.body();
                output.onFilmReceived(filmList);
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
            }
        });
    }

}
