package jaa.com.likeastarapp.modules.filmDetail.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.dao.FilmImage;
import jaa.com.likeastarapp.common.database.FilmDatabase;
import jaa.com.likeastarapp.common.network.FilmImageApi;
import jaa.com.likeastarapp.common.network.FilmService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FilmDetailRepository implements FilmDetailRepositoryInput {

    private FilmDetailRepositoryOutput output;
    private static Retrofit retrofit = null;
    private FilmImageApi service;
    private Context context;
    private MutableLiveData<Film> detailFilm;
    private MutableLiveData<FilmImage> filmImage;

    private static final String DATABASE_NAME = "film_db";
    private FilmDatabase filmDatabase;

    public FilmDetailRepository(FilmDetailRepositoryOutput output, Context context) {
        this.output = output;
        service = FilmService.getRetrofitInstance().create(FilmImageApi.class);
        this.context = context;
        detailFilm = new MutableLiveData<>();
        detailFilm.setValue(new Film());

        filmImage = new MutableLiveData<>();

        filmDatabase = Room.databaseBuilder(this.context,
                FilmDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
    }

    public MutableLiveData<Film> getDetailFilm() {
        return detailFilm;
    }

    @Override
    public MutableLiveData<FilmImage> getFilmImage() {
        return filmImage;
    }

    @Override
    public void getImageFromTitle(String title) {
        Call<FilmImage> call = service.getFilmImage("ee8f32ba",title);
        call.enqueue(new Callback<FilmImage>() {
            @Override
            public void onResponse(Call<FilmImage> call, Response<FilmImage> response) {
                filmImage.setValue(response.body());
            }

            @Override
            public void onFailure(Call<FilmImage> call, Throwable t) {
            }
        });
    }

    @Override
    public void changeVisitState(String title) {
        new ReadDatabaseOperation().execute(title);
    }

    @Override
    public void addDetailFilm(Film film) {
        detailFilm.setValue(film);
        getImageFromTitle(film.getTitle());
    }

    private class UpdateDatabaseOperation extends AsyncTask<Film, Void, Void> {
        @Override
        protected Void doInBackground(Film... params) {

            filmDatabase.databaseAccess().updateFilm(params[0]);

            return null;
        }
    }

    private class ReadDatabaseOperation extends AsyncTask<String, Void, Film> {
        @Override
        protected Film doInBackground(String... params) {

            Film film = filmDatabase.databaseAccess().fetchOneFilmbyFilmTitle(params[0]);

            return film;
        }

        @Override
        protected void onPostExecute(Film result) {
            if(result.isVisited()) {
                result.setVisited(false);
                new UpdateDatabaseOperation().execute(result);
            }
            else {
                result.setVisited(true);
                new UpdateDatabaseOperation().execute(result);
            }
            detailFilm.setValue(result);
        }
    }

}
