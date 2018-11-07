package jaa.com.likeastarapp.modules.filmList.model;

import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;

public interface FilmListModelOutput {
    void onFilmReceived(List<Film> films);
}
