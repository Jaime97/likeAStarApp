package jaa.com.likeastarapp.modules.filmList;

public interface FilmListContract {

    interface View {

    }

    interface Presenter {
        void start(View view);
        void stop();
    }

}
