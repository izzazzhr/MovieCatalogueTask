package com.example.moviecataloguetask.networks;

import android.graphics.Movie;

import com.example.moviecataloguetask.models.AiringToday;
import com.example.moviecataloguetask.models.AiringTodayResponse;
import com.example.moviecataloguetask.models.NowPlayingResponse;
import com.example.moviecataloguetask.models.tv.TvShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TvApiInterface {

    @GET("airing_today")
    Call<AiringTodayResponse> getAiringToday(
            @Query("api_key") String apiKey
    );



    @GET("tv/{tv_id}")
    Call<TvShow> getTvShow(
            @Path("tv_id") String id,
            @Query("api_key") String apiKey
    );


}
