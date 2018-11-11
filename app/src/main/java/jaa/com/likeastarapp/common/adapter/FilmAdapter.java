package jaa.com.likeastarapp.common.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import jaa.com.likeastarapp.R;
import jaa.com.likeastarapp.common.dao.Film;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private List<Film> mDataset;
    private Context context;
    private final ClickListener listener;

    public interface ClickListener {
        void onPositionClicked(int position);
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, director;
        public ImageView placeVisited;
        public ImageButton favouriteButton;
        private WeakReference<ClickListener> listenerRef;

        public FilmViewHolder(View view, ClickListener listener) {
            super(view);
            listenerRef = new WeakReference<>(listener);
            title = (TextView) view.findViewById(R.id.titleTextView);
            director = (TextView) view.findViewById(R.id.directorTextView);
            placeVisited = view.findViewById(R.id.visitedImage);
            favouriteButton = view.findViewById(R.id.favouriteButton);
            favouriteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }

    public FilmAdapter(List<Film> dataSet, Context context, ClickListener listener) {
        mDataset = dataSet;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public FilmAdapter.FilmViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_list_row, parent, false);

        FilmViewHolder vh = new FilmViewHolder(itemView, listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        Film film = mDataset.get(position);
        holder.title.setText(film.getTitle());
        holder.director.setText(film.getDirector());
        if(film.isFavourite()) {
            Drawable image =  context.getResources().getDrawable(R.drawable.star_selected);
            holder.favouriteButton.setBackground(image);
        }
        else {
            Drawable image =  context.getResources().getDrawable(R.drawable.star_unselected);
            holder.favouriteButton.setBackground(image);
        }
        if(film.isVisited()) {
            holder.placeVisited.setVisibility(View.VISIBLE);
        }
        else {
            holder.placeVisited.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
