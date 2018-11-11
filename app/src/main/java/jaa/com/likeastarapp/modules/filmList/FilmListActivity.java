package jaa.com.likeastarapp.modules.filmList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import jaa.com.likeastarapp.R;
import jaa.com.likeastarapp.common.adapter.FilmAdapter;
import jaa.com.likeastarapp.common.dao.Film;

public class FilmListActivity extends AppCompatActivity implements FilmListContract.View {

    private FilmListContract.Presenter presenter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = findViewById(R.id.film_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        presenter = new FilmListPresenter();
        presenter.start(this);
    }

    @Override
    public void updateList(List<Film> movieList) {
        mAdapter = new FilmAdapter(movieList, this, new FilmAdapter.ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                presenter.favouriteButtonClicked(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void reloadList() {
        mAdapter.notifyDataSetChanged();
    }
}
