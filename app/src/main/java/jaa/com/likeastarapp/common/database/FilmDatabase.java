package jaa.com.likeastarapp.common.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import jaa.com.likeastarapp.common.dao.Film;

@Database(entities = {Film.class}, version = 1, exportSchema = false)
public abstract class FilmDatabase extends RoomDatabase {
    public abstract DatabaseAccess databaseAccess() ;
}
