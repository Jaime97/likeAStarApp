package jaa.com.likeastarapp.modules.filmDetail.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jaa.com.likeastarapp.common.dao.FilmImage;
import jaa.com.likeastarapp.common.network.FilmImageApi;
import jaa.com.likeastarapp.common.network.FilmService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FilmDetailModel implements FilmDetailModelInput {

    private FilmDetailModelOutput output;
    private static Retrofit retrofit = null;
    private FilmImageApi service;

    public FilmDetailModel(FilmDetailModelOutput output) {
        this.output = output;
        service = FilmService.getRetrofitInstance().create(FilmImageApi.class);
    }

    @Override
    public void getImageFromTitle(String title) {
        Call<FilmImage> call = service.getFilmImage("ee8f32ba",title);
        call.enqueue(new Callback<FilmImage>() {
            @Override
            public void onResponse(Call<FilmImage> call, Response<FilmImage> response) {
                FilmImage filmImage = response.body();
                output.imageReceived(filmImage);
            }

            @Override
            public void onFailure(Call<FilmImage> call, Throwable t) {
            }
        });
    }

}
