package com.example.moviecataloguetask.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviecataloguetask.R;
import com.example.moviecataloguetask.models.tv.TvShow;
import com.example.moviecataloguetask.networks.Const;
import com.example.moviecataloguetask.networks.TvApiClient;
import com.example.moviecataloguetask.networks.TvApiInterface;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivityTVShow extends AppCompatActivity {
    private TvShow tvShows;
    ImageView ivPoster;
    TextView tvTitle, firstAirDate, rating, tvOverview, tvEpisodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_t_v_show);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(getIntent().getStringExtra("TITLE"));

        }
        ivPoster = findViewById(R.id.iv_poster);
        tvTitle = findViewById(R.id.tv_title);
        firstAirDate = findViewById(R.id.tv_firstAirDate);
        rating = findViewById(R.id.tv_rating);
        tvEpisodes = findViewById(R.id.tv_episodes);
        tvOverview = findViewById(R.id.tv_overview);


        loadData(getIntent().getStringExtra("ID"));




    }

    private void loadData(String id) {
        TvApiInterface tvApiInterface = TvApiClient.getTvDetail().create(TvApiInterface.class);
        Call<TvShow> tvShowCall = tvApiInterface.getTvShow(id, Const.API_KEY);
        tvShowCall.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                System.out.println("URL :: "+ response.raw().request().url());
                if(response.isSuccessful() && response.body() != null){
                    tvShows = response.body();
                    addValue();
                }else{
                    Toast.makeText(DetailActivityTVShow.this, "Request Error :: " + response.errorBody(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                Toast.makeText(DetailActivityTVShow.this, "Network Error :: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void addValue(){

        tvTitle.setText(tvShows.getTitle());
        Glide.with(DetailActivityTVShow.this).load(Const.IMG_URL_300 + tvShows.getImgUrl()).into(ivPoster);
        firstAirDate.setText(tvShows.getFirstAirDate());
        rating.setText(tvShows.getRatings());
        tvEpisodes.setText(tvShows.getEpisodes());
        tvOverview.setText(tvShows.getOverview());

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
