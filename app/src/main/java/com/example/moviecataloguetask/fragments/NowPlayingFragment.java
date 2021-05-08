package com.example.moviecataloguetask.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecataloguetask.R;
import com.example.moviecataloguetask.activities.DetailActivityMovie;
import com.example.moviecataloguetask.adapters.NowPlayingAdapter;
import com.example.moviecataloguetask.models.NowPlaying;
import com.example.moviecataloguetask.models.NowPlayingResponse;
import com.example.moviecataloguetask.networks.Const;
import com.example.moviecataloguetask.networks.MovieApiClient;
import com.example.moviecataloguetask.networks.MovieApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingFragment extends Fragment implements NowPlayingAdapter.OnItemClick {

    private static final String TAG = "NowPlayingFragment";
    private RecyclerView recyclerView;
    private NowPlayingAdapter adapter;
    private List<NowPlaying> nowPlayings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        recyclerView = view.findViewById(R.id.rv_now_playing);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        loadData();
        return view;
    }

    private void loadData() {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit().create(MovieApiInterface.class);

        Call<NowPlayingResponse> nowPlayingCall = movieApiInterface.getNowPlaying(Const.API_KEY);
        nowPlayingCall.enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(Call<NowPlayingResponse> call, Response<NowPlayingResponse> response) {
                if (response.isSuccessful() && response.body().getNowPlayings() != null){
                    nowPlayings = response.body().getNowPlayings();
                    adapter = new NowPlayingAdapter(nowPlayings, NowPlayingFragment.this);
                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(int pos) {
        String s = nowPlayings.get(pos).getTitle();
        Intent detailActivity = new Intent(getActivity(), DetailActivityMovie.class);
        detailActivity.putExtra("ID", nowPlayings.get(pos).getId());
        detailActivity.putExtra("TITLE", nowPlayings.get(pos).getTitle());
//        detailActivity.putExtra("IMG_URL", nowPlayings.get(pos).getImgUrl());
//        detailActivity.putExtra("RELEASE_DATE", nowPlayings.get(pos).getReleaseDate());
//        detailActivity.putExtra("RATINGS", nowPlayings.get(pos).getRatings());
//        detailActivity.putExtra("OVERVIEW", nowPlayings.get(pos).getOverview());
        startActivity(detailActivity);

    }
}
