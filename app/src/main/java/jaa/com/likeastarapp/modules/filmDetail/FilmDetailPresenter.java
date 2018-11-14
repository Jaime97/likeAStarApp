package jaa.com.likeastarapp.modules.filmDetail;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.dao.FilmImage;
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
        Film film = userInterface.getFilm();
        userInterface.addTitleToTextView(film.getTitle());
        userInterface.addActorToTextView(film.getActor_1());
        userInterface.addDirectorToTextView(film.getDirector());
        userInterface.addProductorToTextView(film.getProduction_company());
        model.getImageFromTitle(film.getTitle());
    }

    @Override
    public void stop() {

    }

    @Override
    public void imageReceived(FilmImage filmImage) {
        userInterface.addFilmImage(filmImage);
    }
}
