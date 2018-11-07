package jaa.com.likeastarapp.modules.filmList;


import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModel;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModelInterface;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModelOutput;

public class FilmListPresenter implements FilmListContract.Presenter, FilmListModelOutput {

    private FilmListContract.View userInterface;
    private FilmListModelInterface model;

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
    public void onFilmReceived(List<Film> films) {
        userInterface.updateList(films);
    }

    @Override
    public void stop() {

    }
}
