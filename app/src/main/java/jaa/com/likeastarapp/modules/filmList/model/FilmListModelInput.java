package jaa.com.likeastarapp.modules.filmList.model;

public interface FilmListModelInput {

    void getFilmList();
    void changeFavouriteStateOfFilm(int position);
    void searchInList(final String nameToSearch);
}
