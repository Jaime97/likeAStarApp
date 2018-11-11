package jaa.com.likeastarapp.modules.filmList;


import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModel;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModelInput;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModelOutput;

public class FilmListPresenter implements FilmListContract.Presenter, FilmListModelOutput {

    private FilmListContract.View userInterface;
    private FilmListModelInput model;

    @Override
    public void start(FilmListContract.View view) {
        userInterface = view;
        model = new FilmListModel(this);
        updateFilmList();
    }

    @Override
    public void updateFilmList() {
        model.getFilmList();
    }

    @Override
    public void favouriteButtonClicked(int position) {
        model.changeFavouriteStateOfFilm(position);
    }

    @Override
    public void onFilmReceived(List<Film> films) {
        userInterface.updateList(films);
    }

    @Override
    public void favouriteStateChanged() {
        userInterface.reloadList();
    }

    @Override
    public void stop() {

    }
}
