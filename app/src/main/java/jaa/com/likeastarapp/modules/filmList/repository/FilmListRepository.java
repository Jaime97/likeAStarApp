package jaa.com.likeastarapp.modules.filmList.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.database.FilmDatabase;
import jaa.com.likeastarapp.common.network.FilmLocationsApi;
import jaa.com.likeastarapp.common.network.FilmService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FilmListRepository implements FilmListRepositoryInput {

    private FilmListRepositoryOutput output;
    private static Retrofit retrofit = null;
    private FilmLocationsApi service;
    private MutableLiveData<List<Film>> filteredFilms;
    private boolean favouriteFilter;
    private Context context;
    private boolean downloadOnlyWithWifi;
    private boolean downloadAutomatically;
    private NetworkInfo mWifi;

    private static final String DATABASE_NAME = "film_db";
    private FilmDatabase filmDatabase;

    private final static int INTERVAL = 1000 * 60;
    Handler mHandler = new Handler();

    Runnable mHandlerTask = new Runnable() {
        @Override
        public void run() {
            updateFilmList();
            mHandler.postDelayed(mHandlerTask, INTERVAL);

        }
    };

    public interface ListOfFilmsInterface {
        void returnListOfFilms(List<Film> films);
    }

    public class FilmCallWrapper {
        public ListOfFilmsInterface listOfFilmsInterface;
        public List<Film> films;
    }

    public FilmListRepository(FilmListRepositoryOutput output, Context context) {
        this.output = output;
        service = FilmService.getRetrofitInstance().create(FilmLocationsApi.class);
        filteredFilms = new MutableLiveData<>();
        filteredFilms.setValue(new ArrayList<Film>());
        this.context = context;
        filmDatabase = Room.databaseBuilder(this.context,
                FilmDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
    }

    @Override
    public MutableLiveData<List<Film>> getFilmList() {
        return filteredFilms;
    }

    @Override
    public void setDownloadOnlyWithWifiPreference() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        downloadOnlyWithWifi = sharedPref.getBoolean("pref_only_wifi", false);
    }

    @Override
    public void setDownloadAutomaticallyPreference() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        downloadAutomatically = sharedPref.getBoolean("pref_automatic_download", false);
        if(downloadAutomatically) {
            startRepeatingTask();
        }

    }

    @Override
    public void startRepeatingTask() {
        mHandlerTask.run();
    }

    @Override
    public void stopRepeatingTask() {
        mHandler.removeCallbacks(mHandlerTask);
    }

    @Override
    public void updateFilmList() {
        new ReadDatabaseOperation().execute(new ListOfFilmsInterface(){
            @Override
            public void returnListOfFilms(List<Film> films) {
                checkDatabaseListWithOnline(films);
            }
        });
    }

    @Override
    public void changeFavouriteStateOfFilm(int position) {
        Film film = filteredFilms.getValue().get(position);
        if(film.isFavourite()) {
            film.setFavourite(false);
        }
        else {
            film.setFavourite(true);
        }
        filteredFilms.setValue(filteredFilms.getValue());
        new UpdateDatabaseOperation().execute(film);
    }

    @Override
    public void searchInList(String nameToSearch) {
        filteredFilms.setValue(new ArrayList<Film>());
        final String lowerCaseName = nameToSearch.toLowerCase();
        new ReadDatabaseOperation().execute(new ListOfFilmsInterface(){
            @Override
            public void returnListOfFilms(List<Film> films) {
                for (int i = 0; i < films.size(); i++) {
                    Film film = films.get(i);
                    if (film.getTitle().toLowerCase().contains(lowerCaseName)) {
                        if(!favouriteFilter || film.isFavourite()) {
                            filteredFilms.getValue().add(film);
                            filteredFilms.setValue(filteredFilms.getValue());
                        }
                    }
                }
            }
        });

    }

    @Override
    public void getFilm(int position) {
        Film film = filteredFilms.getValue().get(position);
        output.returnFilm(film);
    }

    @Override
    public void setFavouriteFilter(boolean favourite) {
        favouriteFilter = favourite;
    }

    private void checkDatabaseListWithOnline(final List<Film> databaseFilms) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if((mWifi.isConnected() && downloadOnlyWithWifi) || !downloadOnlyWithWifi) {
            Call<List<Film>> call = service.getFilmList();
            call.enqueue(new Callback<List<Film>>() {
                @Override
                public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                    manageFilmListResults(databaseFilms, response.body());
                }

                @Override
                public void onFailure(Call<List<Film>> call, Throwable t) {
                }
            });
        }
        else {
            List<Film> empltyFilmList = new ArrayList<>();
            manageFilmListResults(databaseFilms, empltyFilmList);
        }
    }

    private void manageFilmListResults(List<Film> databaseFilms, List<Film> onlineFilms) {
        filteredFilms.setValue(new ArrayList<Film>());
        onlineFilms = orderFilmListResult(onlineFilms);
        for(Film databaseFilm : databaseFilms) {
            if(!filteredFilms.getValue().contains(databaseFilm)) {
                if(!favouriteFilter || databaseFilm.isFavourite()) {
                    filteredFilms.getValue().add(databaseFilm);
                    filteredFilms.setValue(filteredFilms.getValue());
                }
            }
        }
        for(Film onlineFilm : onlineFilms) {
            if(!filteredFilms.getValue().contains(onlineFilm)) {
                if(!favouriteFilter || onlineFilm.isFavourite()) {
                    filteredFilms.getValue().add(onlineFilm);
                    filteredFilms.setValue(filteredFilms.getValue());
                }
            }
            if(!databaseFilms.contains(onlineFilm)) {
                new WriteDatabaseOperation().execute(onlineFilm);
            }
        }
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

    private class ReadDatabaseOperation extends AsyncTask<ListOfFilmsInterface, Void, FilmCallWrapper> {
        @Override
        protected FilmCallWrapper doInBackground(ListOfFilmsInterface... params) {

            List<Film> films = filmDatabase.databaseAccess().getAllFilms();
            FilmCallWrapper filmCallWrapper = new FilmCallWrapper();
            filmCallWrapper.films = films;
            filmCallWrapper.listOfFilmsInterface = params[0];
            return filmCallWrapper;
        }

        @Override
        protected void onPostExecute(FilmCallWrapper filmCallWrapper) {
            filmCallWrapper.listOfFilmsInterface.returnListOfFilms(filmCallWrapper.films);
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
