package jaa.com.likeastarapp.modules.filmList;


import android.content.Context;
import android.content.Intent;

import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.modules.filmDetail.FilmDetailActivity;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModel;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModelInput;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModelOutput;

public class FilmListPresenter implements FilmListContract.Presenter, FilmListModelOutput {

    private FilmListContract.View userInterface;
    private FilmListModelInput model;

    @Override
    public void start(FilmListContract.View view, Context context) {
        userInterface = view;
        model = new FilmListModel(this, context);
    }

    @Override
    public void resume() {
        updateFilmList();
    }

    @Override
    public void updateFilmList() {
        model.getFilmList();
    }

    @Override
    public void rowClicked(int position) {
        model.getFilm(position);
    }

    @Override
    public void favouriteButtonClicked(int position) {
        model.changeFavouriteStateOfFilm(position);
    }

    @Override
    public void searchInList(String nameToSearch) {
        model.searchInList(nameToSearch);
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
    public void searchDone(List<Film> films) {
        userInterface.updateList(films);
    }

    @Override
    public void returnFilm(Film film) {
        userInterface.initDetailWithFilm(film);
    }

    @Override
    public void stop() {

    }
}
