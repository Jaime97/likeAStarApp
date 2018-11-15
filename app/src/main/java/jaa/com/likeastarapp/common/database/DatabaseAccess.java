package jaa.com.likeastarapp.common.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import jaa.com.likeastarapp.common.dao.Film;

@Dao
public interface DatabaseAccess {
    @Insert
    void insertOnlySingleFilm (Film film);

    @Insert
    void insertMultipleFilms (List<Film> filmList);

    @Query("SELECT * FROM Film WHERE title = :title")
    Film fetchOneFilmbyFilmTitle (String title);

    @Query("SELECT * FROM Film")
    List<Film> getAllFilms();

    @Update
    void updateFilm (Film film);

    @Delete
    void deleteFilm (Film film);
}
