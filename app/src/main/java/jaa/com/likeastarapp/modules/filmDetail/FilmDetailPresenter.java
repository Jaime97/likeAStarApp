package jaa.com.likeastarapp.modules.filmDetail;


import jaa.com.likeastarapp.modules.filmDetail.model.FilmDetailModel;
import jaa.com.likeastarapp.modules.filmDetail.model.FilmDetailModelInput;
import jaa.com.likeastarapp.modules.filmDetail.model.FilmDetailModelOutput;

public class FilmDetailPresenter implements FilmDetailContract.Presenter, FilmDetailModelOutput {

    private FilmDetailContract.View userInterface;
    private FilmDetailModelInput model;

    @Override
    public void start(FilmDetailContract.View view) {
        userInterface = view;
        model = new FilmDetailModel(this);
    }

    @Override
    public void stop() {

    }
}
