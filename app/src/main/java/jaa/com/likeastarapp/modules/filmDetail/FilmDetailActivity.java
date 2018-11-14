package jaa.com.likeastarapp.modules.filmDetail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import jaa.com.likeastarapp.R;
import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.common.dao.FilmImage;

public class FilmDetailActivity extends AppCompatActivity implements FilmDetailContract.View {

    public static final String FILM_TO_SEND = "filmSend";

    private FilmDetailContract.Presenter presenter;
    private ImageView filmImage;
    private TextView directorTextview, actorTextview, productorTextview, titleTextView;
    private Button visitedButton, zoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        filmImage = findViewById(R.id.filmImage);
        directorTextview = findViewById(R.id.directorTextView);
        actorTextview = findViewById(R.id.actorTextView);
        productorTextview = findViewById(R.id.productorTextView);
        titleTextView = findViewById(R.id.titleTextView);
        presenter = new FilmDetailPresenter();
        presenter.start(this);
    }

    @Override
    public Film getFilm() {
        Bundle b = this.getIntent().getExtras();
        Film film = new Film();
        if (b != null)
            film =(Film) b.getSerializable(FILM_TO_SEND);
        return film;
    }

    @Override
    public void addDirectorToTextView(String director) {
        directorTextview.setText(getString(R.string.directed) + " " + director);
    }

    @Override
    public void addTitleToTextView(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void addActorToTextView(String actor) {
        actorTextview.setText(getString(R.string.starring) + " " + actor);
    }

    @Override
    public void addProductorToTextView(String productor) {
        productorTextview.setText(getString(R.string.produced) + " " + productor);
    }

    @Override
    public void addFilmImage(FilmImage image) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(image.getPoster(), filmImage);
    }

}
