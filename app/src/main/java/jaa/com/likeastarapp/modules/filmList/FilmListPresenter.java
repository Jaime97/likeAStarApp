package jaa.com.likeastarapp.modules.filmList;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;

import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModel;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModelInput;
import jaa.com.likeastarapp.modules.filmList.model.FilmListModelOutput;

public class FilmListPresenter implements FilmListContract.Presenter, FilmListModelOutput {

    private FilmListContract.View userInterface;
    private FilmListModelInput model;
    private String nameToSearch;

    @Override
    public void start(FilmListContract.View view, Context context) {
        userInterface = view;
        model = new FilmListModel(this, context);
        model.getFilmList().observe(userInterface.getActivity(), new Observer<List<Film>>() {
            @Override
            public void onChanged(@Nullable List<Film> films) {
                userInterface.updateList(films);
            }
        });
        nameToSearch = "";
    }

    @Override
    public void resume() {
        model.setDownloadAutomaticallyPreference();
        model.setDownloadOnlyWithWifiPreference();
        model.updateFilmList();
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
        this.nameToSearch = nameToSearch;
        model.searchInList(nameToSearch);
    }

    @Override
    public void favouriteTabSelected() {
        model.setFavouriteFilter(true);
        model.searchInList(nameToSearch);
    }

    @Override
    public void allElementsTabSelected() {
        model.setFavouriteFilter(false);
        model.searchInList(nameToSearch);
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
        model.stopRepeatingTask();
    }
}
