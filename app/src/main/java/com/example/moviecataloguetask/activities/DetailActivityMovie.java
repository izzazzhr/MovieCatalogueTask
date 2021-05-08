package com.example.moviecataloguetask.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.graphics.Movie;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviecataloguetask.R;
import com.example.moviecataloguetask.models.movie.MovieModel;
import com.example.moviecataloguetask.networks.Const;
import com.example.moviecataloguetask.networks.MovieApiClient;
import com.example.moviecataloguetask.networks.MovieApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivityMovie extends AppCompatActivity {
    private MovieModel movies;

    ImageView ivPoster;
    TextView tvTitle, releaseDate, rating, tvOverview, tvDuration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));

        }
        ivPoster = findViewById(R.id.iv_poster);
        tvTitle = findViewById(R.id.tv_title);
        releaseDate = findViewById(R.id.tv_releaseDate);
        rating = findViewById(R.id.tv_rating);
        tvDuration = findViewById(R.id.tv_duration);
        tvOverview = findViewById(R.id.tv_overview);
        loadData(getIntent().getStringExtra("ID"));



    }

    private void loadData(String id) {
        System.out.println("ID : "+id);
        MovieApiInterface movieApiInterface = MovieApiClient.getMovieDetail().create(MovieApiInterface.class);
        Call<MovieModel> movieCall = movieApiInterface.getMovie(id, Const.API_KEY);
        movieCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if(response.isSuccessful() && response.body() != null){
                    movies = response.body();
                    addValue();
                }else{
                    Toast.makeText(DetailActivityMovie.this, "Request Error :: " + response.errorBody(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Toast.makeText(DetailActivityMovie.this, "Network error :: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void addValue(){

        tvTitle.setText(movies.getTitle());
        Glide.with(DetailActivityMovie.this).load(Const.IMG_URL_300 + movies.getImgUrl()).into(ivPoster);
        tvOverview.setText(movies.getOverview());
        rating.setText(movies.getRatings());
        releaseDate.setText(movies.getRatings());
        tvDuration.setText(movies.getDuration());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}