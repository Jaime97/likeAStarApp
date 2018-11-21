package jaa.com.likeastarapp.modules.filmDetail.model;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.dao.FilmImage;

public interface FilmDetailModelOutput {
    void imageReceived(FilmImage filmImage);
    void filmReceived(Film film);
}
