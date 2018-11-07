package jaa.com.likeastarapp.modules.filmList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jaa.com.likeastarapp.R;

public class FilmListActivity extends AppCompatActivity implements FilmListContract.View {

    private FilmListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        presenter = new FilmListPresenter();
        presenter.start(this);
    }
}
