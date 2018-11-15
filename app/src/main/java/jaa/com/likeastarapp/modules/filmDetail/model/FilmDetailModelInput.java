package jaa.com.likeastarapp.modules.filmDetail.model;

import jaa.com.likeastarapp.common.dao.Film;

public interface FilmDetailModelInput {
    void getImageFromTitle(String title);
    void changeVisitedState(Film film, boolean visited);
    void getFilm(String title);
}
