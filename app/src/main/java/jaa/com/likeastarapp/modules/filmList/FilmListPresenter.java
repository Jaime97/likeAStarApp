package jaa.com.likeastarapp.modules.filmList;


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
    }

    @Override
    public void stop() {

    }
}
