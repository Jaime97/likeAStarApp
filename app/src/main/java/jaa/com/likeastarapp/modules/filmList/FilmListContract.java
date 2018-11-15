package jaa.com.likeastarapp.modules.filmList;

import android.content.Context;

import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;

public interface FilmListContract {

    interface View {
        void updateList(List<Film> movieList);
        void initDetailWithFilm(Film film);
        void initSettings();
        void reloadList();
    }

    interface Presenter {
        void start(View view, Context context);
        void resume();
        void updateFilmList();
        void rowClicked(int position);
        void favouriteButtonClicked(int position);
        void searchInList(String nameToSearch);
        void favouriteTabSelected();
        void allElementsTabSelected();
        void onSettingsButtonCicked();
        void stop();
    }

}
