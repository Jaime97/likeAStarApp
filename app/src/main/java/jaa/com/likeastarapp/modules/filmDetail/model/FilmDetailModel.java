package jaa.com.likeastarapp.modules.filmDetail.model;

import jaa.com.likeastarapp.common.network.FilmLocationsApi;
import jaa.com.likeastarapp.common.network.FilmService;
import retrofit2.Retrofit;

public class FilmDetailModel implements FilmDetailModelInput {

    private FilmDetailModelOutput output;
    private static Retrofit retrofit = null;
    private FilmLocationsApi service;

    public FilmDetailModel(FilmDetailModelOutput output) {
        this.output = output;
        service = FilmService.getRetrofitInstance().create(FilmLocationsApi.class);
    }

}
