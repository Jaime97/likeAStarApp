package jaa.com.likeastarapp.modules.filmDetail;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;

import jaa.com.likeastarapp.R;
import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.dao.FilmImage;
import jaa.com.likeastarapp.modules.filmDetail.repository.FilmDetailRepository;
import jaa.com.likeastarapp.modules.filmDetail.repository.FilmDetailRepositoryInput;
import jaa.com.likeastarapp.modules.filmDetail.repository.FilmDetailRepositoryOutput;

public class FilmDetailPresenter implements FilmDetailContract.Presenter, FilmDetailRepositoryOutput {

    private FilmDetailContract.View userInterface;
    private FilmDetailRepositoryInput repository;

    @Override
    public void start(FilmDetailContract.View view, Context context) {
        userInterface = view;
        repository = new FilmDetailRepository(this, context);
        repository.getDetailFilm().observe(userInterface.getActivity(), new Observer<Film>() {
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
        repository.getFilmImage().observe(userInterface.getActivity(), new Observer<FilmImage>() {
            @Override
            public void onChanged(@Nullable FilmImage filmImage) {
                userInterface.addFilmImage(filmImage);
            }
        });
        Film film = userInterface.getFilm();
        repository.addDetailFilm(film);
        repository.getImageFromTitle(film.getTitle());
    }

    @Override
    public void zoneButtonClicked() {
        userInterface.initMapWithFilm(userInterface.getFilm());
    }

    @Override
    public void visitedButtonClicked() {
        Film film = userInterface.getFilm();
        repository.changeVisitState(film.getTitle());
    }

    @Override
    public void stop() {

    }
}
