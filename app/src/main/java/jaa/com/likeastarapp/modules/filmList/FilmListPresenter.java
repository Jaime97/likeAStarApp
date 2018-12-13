package jaa.com.likeastarapp.modules.filmList;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;

import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.modules.filmList.repository.FilmListRepository;
import jaa.com.likeastarapp.modules.filmList.repository.FilmListRepositoryInput;
import jaa.com.likeastarapp.modules.filmList.repository.FilmListRepositoryOutput;

public class FilmListPresenter implements FilmListContract.Presenter, FilmListRepositoryOutput {

    private FilmListContract.View userInterface;
    private FilmListRepositoryInput repository;
    private String nameToSearch;

    @Override
    public void start(FilmListContract.View view, Context context) {
        userInterface = view;
        repository = new FilmListRepository(this, context);
        repository.getFilmList().observe(userInterface.getActivity(), new Observer<List<Film>>() {
            @Override
            public void onChanged(@Nullable List<Film> films) {
                userInterface.updateList(films);
            }
        });
        nameToSearch = "";
    }

    @Override
    public void resume() {
        repository.setDownloadAutomaticallyPreference();
        repository.setDownloadOnlyWithWifiPreference();
        repository.updateFilmList();
    }

    @Override
    public void rowClicked(int position) {
        repository.getFilm(position);
    }

    @Override
    public void favouriteButtonClicked(int position) {
        repository.changeFavouriteStateOfFilm(position);
    }

    @Override
    public void searchInList(String nameToSearch) {
        this.nameToSearch = nameToSearch;
        repository.searchInList(nameToSearch);
    }

    @Override
    public void favouriteTabSelected() {
        repository.setFavouriteFilter(true);
        repository.searchInList(nameToSearch);
    }

    @Override
    public void allElementsTabSelected() {
        repository.setFavouriteFilter(false);
        repository.searchInList(nameToSearch);
    }

    @Override
    public void onSettingsButtonCicked() {
        userInterface.initSettings();
    }

    @Override
    public void returnFilm(Film film) {
        userInterface.initDetailWithFilm(film);
    }

    @Override
    public void stop() {
        repository.stopRepeatingTask();
    }
}
