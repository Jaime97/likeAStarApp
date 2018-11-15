package jaa.com.likeastarapp.modules.filmList.model;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.database.FilmDatabase;
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
    private List<Film> filteredFilms;
    private Context context;

    private static final String DATABASE_NAME = "film_db";
    private FilmDatabase filmDatabase;

    public FilmListModel(FilmListModelOutput output, Context context) {
        this.output = output;
        service = FilmService.getRetrofitInstance().create(FilmLocationsApi.class);
        orderedFilms = new ArrayList<>();
        filteredFilms = new ArrayList<>();
        this.context = context;
        filmDatabase = Room.databaseBuilder(this.context,
                FilmDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
    }

    @Override
    public void getFilmList() {
        new ReadDatabaseOperation().execute();
    }


    @Override
    public void changeFavouriteStateOfFilm(int position) {
        Film film = filteredFilms.get(position);
        if(film.isFavourite()) {
            film.setFavourite(false);
        }
        else {
            film.setFavourite(true);
        }
        new UpdateDatabaseOperation().execute(film);
        output.favouriteStateChanged();
    }

    @Override
    public void searchInList(String nameToSearch) {
        filteredFilms = new ArrayList<>();
        String lowerCaseName = nameToSearch.toLowerCase();
        if(!nameToSearch.equals("")) {
            for (int i = 0; i < orderedFilms.size(); i++) {
                Film film = orderedFilms.get(i);
                if (film.getTitle().toLowerCase().contains(lowerCaseName)) {
                    filteredFilms.add(film);
                }
            }
        }
        else {
            filteredFilms.addAll(orderedFilms);
        }
        output.searchDone(filteredFilms);
    }

    @Override
    public void getFilm(int position) {
        Film film = filteredFilms.get(position);
        output.returnFilm(film);
    }

    private void checkDatabaseListWithOnline(final List<Film> databaseFilms) {
        Call<List<Film>> call = service.getFilmList();
        call.enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                List<Film> filmList = response.body();
                orderedFilms = new ArrayList<>();
                filteredFilms = new ArrayList<>();
                orderedFilms = orderFilmListResult(filmList);
                filteredFilms.addAll(databaseFilms);
                for(Film onlineFilm : orderedFilms) {
                    if(!filteredFilms.contains(onlineFilm)) {
                        new WriteDatabaseOperation().execute(onlineFilm);
                        filteredFilms.add(onlineFilm);
                    }
                }

                output.onFilmReceived(filteredFilms);
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
            }
        });
    }

    private List<Film> orderFilmListResult(List<Film> resultList) {
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

    private class ReadDatabaseOperation extends AsyncTask<Void, Void, List<Film>> {
        @Override
        protected List<Film> doInBackground(Void... params) {

            List<Film> films = filmDatabase.databaseAccess().getAllFilms();

            return films;
        }

        @Override
        protected void onPostExecute(List<Film> result) {
            checkDatabaseListWithOnline(result);
        }
    }

    private class WriteDatabaseOperation extends AsyncTask<Film, Void, Void> {
        @Override
        protected Void doInBackground(Film... params) {

            filmDatabase.databaseAccess().insertOnlySingleFilm(params[0]);

            return null;
        }
    }

    private class UpdateDatabaseOperation extends AsyncTask<Film, Void, Void> {
        @Override
        protected Void doInBackground(Film... params) {

            filmDatabase.databaseAccess().updateFilm(params[0]);

            return null;
        }
    }

}
