package jaa.com.likeastarapp.modules.filmDetail.repository;

import android.arch.lifecycle.MutableLiveData;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.dao.FilmImage;

public interface FilmDetailRepositoryInput {
    void getImageFromTitle(String title);
    void changeVisitState(String title);
    void addDetailFilm(Film film);
    MutableLiveData<Film> getDetailFilm();
    MutableLiveData<FilmImage> getFilmImage();
}
