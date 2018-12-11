package jaa.com.likeastarapp.modules.filmList.model;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;

public interface FilmListModelInput {

    void updateFilmList();
    void changeFavouriteStateOfFilm(int position);
    void searchInList(final String nameToSearch);
    void getFilm(int position);
    void setFavouriteFilter(boolean favourite);
    void setDownloadOnlyWithWifiPreference();
    void setDownloadAutomaticallyPreference();
    void startRepeatingTask();
    void stopRepeatingTask();
    MutableLiveData<List<Film>> getFilmList();
}
