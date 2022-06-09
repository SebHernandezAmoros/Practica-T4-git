package com.example.evaluacionpractica2.Service;

import com.example.evaluacionpractica2.Entities.Anime;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AnimeService {
    @GET("animes")
    Call<List<Anime>> GetAnimes();
    @POST("animes")
    Call<Anime> createAnime(@Body Anime anime);
    @PUT("animes/{id}")
    Call<Anime> update(@Path("id") int id, @Body Anime anime);
}
