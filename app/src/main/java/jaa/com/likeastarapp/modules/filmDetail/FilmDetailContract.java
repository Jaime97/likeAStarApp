package jaa.com.likeastarapp.modules.filmDetail;

public interface FilmDetailContract {

    interface View {
    }

    interface Presenter {
        void start(View view);
        void stop();
    }

}
