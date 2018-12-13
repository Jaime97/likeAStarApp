package jaa.com.likeastarapp.modules.filmList;

import android.content.Context;

import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;

public interface FilmListContract {

    interface View {
        void updateList(List<Film> movieList);
        void initDetailWithFilm(Film film);
        void initSettings();
        FilmListActivity getActivity();
    }

    interface Presenter {
        void start(View view, Context context);
        void resume();
        void rowClicked(int position);
        void favouriteButtonClicked(int position);
        void searchInList(String nameToSearch);
        void favouriteTabSelected();
        void allElementsTabSelected();
        void onSettingsButtonCicked();
        void stop();
    }

}
