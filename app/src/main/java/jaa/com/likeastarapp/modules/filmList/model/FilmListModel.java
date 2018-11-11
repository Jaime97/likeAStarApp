package jaa.com.likeastarapp.modules.filmList.model;

import java.util.ArrayList;
import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.network.FilmLocationsApi;
import jaa.com.likeastarapp.common.network.FilmService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FilmListModel implements FilmListModelInput {

    private FilmListModelOutput output;
    private static Retrofit retrofit = null;
    private FilmLocationsApi service;
    private List<Film> orderedFilms;

    public FilmListModel(FilmListModelOutput output) {
        this.output = output;
        service = FilmService.getRetrofitInstance().create(FilmLocationsApi.class);
        orderedFilms = new ArrayList<>();
    }

    @Override
    public void getFilmList() {
        Call<List<Film>> call = service.getFilmList();
        call.enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                List<Film> filmList = response.body();
                orderedFilms = orderFilmListResult(filmList);
                output.onFilmReceived(orderedFilms);
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
            }
        });
    }

    @Override
    public void changeFavouriteStateOfFilm(int position) {
        Film film = orderedFilms.get(position);
        if(film.isFavourite()) {
            film.setFavourite(false);
        }
        else {
            film.setFavourite(true);
        }
        output.favouriteStateChanged();
    }

    public List<Film> orderFilmListResult(List<Film> resultList) {
        List<Film> orderedFilmList = new ArrayList<>();
        for(Film film : resultList) {
            if(orderedFilmList.contains(film)) {
                Film filmInList = orderedFilmList.get(orderedFilmList.indexOf(film));
                filmInList.getOrderedLocations().add(film.getLocations());
            }
            else {
                orderedFilmList.add(film);
                film.getOrderedLocations().add(film.getLocations());
            }
        }
        return orderedFilmList;
    }

}
