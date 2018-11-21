package jaa.com.likeastarapp.modules.filmDetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

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
        visitedButton = findViewById(R.id.visitedButton);
        zoneButton = findViewById(R.id.zoneButton);
        visitedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.visitedButtonClicked();
            }
        });
        zoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.zoneButtonClicked();
            }
        });
        presenter = new FilmDetailPresenter();
        presenter.start(this, getApplicationContext());
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
    public void initMapWithFilm(Film film) {
        String uri = "http://maps.google.co.in/maps?q=" + film.getLocations();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    @Override
    public void addFilmImage(FilmImage image) {
        Picasso.get().load(image.getPoster()).placeholder(R.drawable.placeholder).into(filmImage);
    }

    @Override
    public void changeVisitedText(int text) {
        visitedButton.setText(text);
    }

}
