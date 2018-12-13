package jaa.com.likeastarapp.modules.filmDetail.model;

import android.arch.lifecycle.MutableLiveData;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.dao.FilmImage;

public interface FilmDetailModelInput {
    void getImageFromTitle(String title);
    void changeVisitState(String title);
    void addDetailFilm(Film film);
    MutableLiveData<Film> getDetailFilm();
    MutableLiveData<FilmImage> getFilmImage();
}
