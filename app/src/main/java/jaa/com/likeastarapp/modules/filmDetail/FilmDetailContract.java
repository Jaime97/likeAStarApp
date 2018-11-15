package jaa.com.likeastarapp.modules.filmDetail;
import android.content.Context;

import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.dao.FilmImage;

public interface FilmDetailContract {

    interface View {
        Film getFilm();
        void addDirectorToTextView(String director);
        void addActorToTextView(String actor);
        void addTitleToTextView(String title);
        void addProductorToTextView(String productor);
        void initMapWithFilm(Film film);
        void addFilmImage(FilmImage image);
        void changeVisitedText(int text);
    }

    interface Presenter {
        void start(View view, Context context);
        void zoneButtonClicked();
        void visitedButtonClicked();
        void stop();
    }

}
