package jaa.com.likeastarapp.modules.filmDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import jaa.com.likeastarapp.R;

public class FilmDetailActivity extends AppCompatActivity implements FilmDetailContract.View {

    private FilmDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        presenter = new FilmDetailPresenter();
        presenter.start(this);
    }

}
