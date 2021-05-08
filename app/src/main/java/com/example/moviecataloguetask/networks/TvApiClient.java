package com.example.moviecataloguetask.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvApiClient {
    private static Retrofit retrofit, retrofitDetail;

    public static Retrofit getRetrofit(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(Const.BASE_URL_TVSHOW).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
    public static Retrofit getTvDetail(){
        if(retrofitDetail==null){
            retrofitDetail = new Retrofit.Builder().baseUrl(Const.BASE_URL_DETAIL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofitDetail;
    }
}
