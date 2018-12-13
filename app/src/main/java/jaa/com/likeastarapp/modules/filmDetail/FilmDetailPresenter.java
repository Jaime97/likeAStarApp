package jaa.com.likeastarapp.modules.filmDetail;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;

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
        model.getDetailFilm().observe(userInterface.getActivity(), new Observer<Film>() {
            @Override
            public void onChanged(@Nullable Film film) {
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
            }
        });
        model.getFilmImage().observe(userInterface.getActivity(), new Observer<FilmImage>() {
            @Override
            public void onChanged(@Nullable FilmImage filmImage) {
                userInterface.addFilmImage(filmImage);
            }
        });
        Film film = userInterface.getFilm();
        model.addDetailFilm(film);
        model.getImageFromTitle(film.getTitle());
    }

    @Override
    public void zoneButtonClicked() {
        userInterface.initMapWithFilm(userInterface.getFilm());
    }

    @Override
    public void visitedButtonClicked() {
        Film film = userInterface.getFilm();
        model.changeVisitState(film.getTitle());
    }

    @Override
    public void stop() {

    }
}
