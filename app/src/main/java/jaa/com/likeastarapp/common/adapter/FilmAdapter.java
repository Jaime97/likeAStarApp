package jaa.com.likeastarapp.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jaa.com.likeastarapp.R;
import jaa.com.likeastarapp.common.dao.Film;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private List<Film> mDataset;

    public static class FilmViewHolder extends RecyclerView.ViewHolder {
        public TextView title, director;
        public FilmViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titleTextView);
            director = (TextView) view.findViewById(R.id.directorTextView);
        }
    }

    public FilmAdapter(List<Film> dataSet) {
        mDataset = dataSet;
    }

    @Override
    public FilmAdapter.FilmViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_list_row, parent, false);

        FilmViewHolder vh = new FilmViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Film film = mDataset.get(position);
        holder.title.setText(film.getTitle());
        holder.director.setText(film.getDirector());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
