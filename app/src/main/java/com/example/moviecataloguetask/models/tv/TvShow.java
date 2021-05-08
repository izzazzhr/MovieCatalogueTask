package com.example.moviecataloguetask.models.tv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvShow {
    private String id;

    @SerializedName("name")
    private String title;


    private String overview;

    @SerializedName("number_of_episodes")
    private String episodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    @SerializedName("poster_path")
    private String imgUrl;

    @SerializedName( "first_air_date")
    private String firstAirDate;

    @SerializedName("vote_average")
    private String ratings;

}
