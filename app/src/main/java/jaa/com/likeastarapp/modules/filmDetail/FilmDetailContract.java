package jaa.com.likeastarapp.modules.filmDetail;
import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.dao.FilmImage;

public interface FilmDetailContract {

    interface View {
        Film getFilm();
        void addDirectorToTextView(String director);
        void addActorToTextView(String actor);
        void addTitleToTextView(String title);
        void addProductorToTextView(String productor);
        void addFilmImage(FilmImage image);
    }

    interface Presenter {
        void start(View view);
        void stop();
    }

}
