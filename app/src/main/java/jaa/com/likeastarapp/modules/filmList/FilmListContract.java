package jaa.com.likeastarapp.modules.filmList;

import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;

public interface FilmListContract {

    interface View {
        void updateList(List<Film> movieList);
    }

    interface Presenter {
        void start(View view);
        void updateFilmList();
        void stop();
    }

}
