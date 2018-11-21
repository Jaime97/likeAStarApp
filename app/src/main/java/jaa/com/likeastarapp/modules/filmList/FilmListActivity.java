package jaa.com.likeastarapp.modules.filmList;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jaa.com.likeastarapp.R;
import jaa.com.likeastarapp.common.adapter.FilmAdapter;
import jaa.com.likeastarapp.common.dao.Film;
import jaa.com.likeastarapp.modules.filmDetail.FilmDetailActivity;
import jaa.com.likeastarapp.modules.settings.SettingsActivity;

public class FilmListActivity extends AppCompatActivity implements FilmListContract.View, TextWatcher {

    public static final String FILM_TO_SEND = "filmSend";

    private FilmListContract.Presenter presenter;

    private RecyclerView mRecyclerView;
    private TextView searchBar;
    private FilmAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TabLayout tabLayout;
    private ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        searchBar = findViewById(R.id.search_bar);
        searchBar.addTextChangedListener(this);

        mRecyclerView = findViewById(R.id.film_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        settingsButton = findViewById(R.id.settingsButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSettingsButtonCicked();
            }
        });

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.all_elements));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.favourites));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tabLayout.getSelectedTabPosition() == 0){
                    presenter.allElementsTabSelected();
                }
                else {
                    presenter.favouriteTabSelected();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        List<Film> emptyList = new ArrayList<>();
        mAdapter = new FilmAdapter(emptyList, this, new FilmAdapter.ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                presenter.favouriteButtonClicked(position);
            }
        }, new FilmAdapter.ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                presenter.rowClicked(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        presenter = new FilmListPresenter();
        presenter.start(this, getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void updateList(List<Film> movieList) {
        mAdapter.update(movieList);
    }

    @Override
    public void reloadList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initDetailWithFilm(Film film) {
        Intent intent = new Intent(this, FilmDetailActivity.class);
        intent.putExtra(FILM_TO_SEND, film);
        startActivity(intent);
    }

    @Override
    public void initSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        presenter.searchInList(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
