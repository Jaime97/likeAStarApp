package jaa.com.likeastarapp.modules.filmList.model;

public interface FilmListModelInput {

    void getFilmList();
    void changeFavouriteStateOfFilm(int position);
    void searchInList(final String nameToSearch);
    void getFilm(int position);
    void setFavouriteFilter(boolean favourite);
    void setDownloadOnlyWithWifiPreference();
    void setDownloadAutomaticallyPreference();
    void startRepeatingTask();
    void stopRepeatingTask();
}
