package jaa.com.likeastarapp.modules.filmDetail;

import android.content.Context;

import jaa.com.likeastarapp.R;
import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.dao.FilmImage;
import jaa.com.likeastarapp.modules.filmDetail.model.FilmDetailModel;
import jaa.com.likeastarapp.modules.filmDetail.model.FilmDetailModelInput;
import jaa.com.likeastarapp.modules.filmDetail.model.FilmDetailModelOutput;

public class FilmDetailPresenter implements FilmDetailContract.Presenter, FilmDetailModelOutput {

    private FilmDetailContract.View userInterface;
    private FilmDetailModelInput model;

    @Override
    public void start(FilmDetailContract.View view, Context context) {
        userInterface = view;
        model = new FilmDetailModel(this, context);
        Film film = userInterface.getFilm();
        if(film.isVisited()) {
            userInterface.changeVisitedText(R.string.visited);
        }
        else {
            userInterface.changeVisitedText(R.string.unvisited);
        }
        userInterface.addTitleToTextView(film.getTitle());
        userInterface.addActorToTextView(film.getActor_1());
        userInterface.addDirectorToTextView(film.getDirector());
        userInterface.addProductorToTextView(film.getProduction_company());
        model.getImageFromTitle(film.getTitle());
    }

    @Override
    public void zoneButtonClicked() {
        userInterface.initMapWithFilm(userInterface.getFilm());
    }

    @Override
    public void visitedButtonClicked() {
        Film film = userInterface.getFilm();
        model.getFilm(film.getTitle());
    }

    @Override
    public void imageReceived(FilmImage filmImage) {
        userInterface.addFilmImage(filmImage);
    }

    @Override
    public void filmReceived(Film film) {
        if(film.isVisited()) {
            model.changeVisitedState(film, false);
            userInterface.changeVisitedText(R.string.unvisited);
        }
        else {
            model.changeVisitedState(film, true);
            userInterface.changeVisitedText(R.string.visited);
        }
    }

    @Override
    public void stop() {

    }
}
