package com.example.moviecataloguetask.networks;

import android.graphics.Movie;


import com.example.moviecataloguetask.models.NowPlayingResponse;
import com.example.moviecataloguetask.models.movie.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiInterface {

    @GET("now_playing")
    Call<NowPlayingResponse> getNowPlaying(
            @Query("api_key") String apiKey
    );



    @GET("movie/{movie_id}")
    Call<MovieModel> getMovie(
            @Path("movie_id") String id,
            @Query("api_key") String apiKey
    );

}
