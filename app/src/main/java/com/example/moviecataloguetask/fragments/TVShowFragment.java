package com.example.moviecataloguetask.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecataloguetask.R;
import com.example.moviecataloguetask.activities.DetailActivityTVShow;
import com.example.moviecataloguetask.adapters.TVShowAdapter;
import com.example.moviecataloguetask.models.AiringToday;
import com.example.moviecataloguetask.models.AiringTodayResponse;
import com.example.moviecataloguetask.networks.Const;
import com.example.moviecataloguetask.networks.TvApiClient;
import com.example.moviecataloguetask.networks.TvApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowFragment extends Fragment implements TVShowAdapter.OnItemClick {
    private static final String TAG = "TVShowFragment";
    private RecyclerView recyclerView;
    private TVShowAdapter adapter;
    private List<AiringToday> airingTodays;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);
        recyclerView = view.findViewById(R.id.rv_tvshow);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        loadData();
        return view;
    }

    private void loadData() {
        TvApiInterface tvApiInterface = TvApiClient.getRetrofit().create(TvApiInterface.class);
        Call<AiringTodayResponse> airingTodayCall = tvApiInterface.getAiringToday(Const.API_KEY);
        airingTodayCall.enqueue(new Callback<AiringTodayResponse>() {
            @Override
            public void onResponse(Call<AiringTodayResponse> call, Response<AiringTodayResponse> response) {
                if(response.isSuccessful() && response.body().getAiringTodays() != null){
                    airingTodays = response.body().getAiringTodays();
                    adapter = new TVShowAdapter(airingTodays, TVShowFragment.this);
                    recyclerView.setAdapter(adapter);
                }else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AiringTodayResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Failed" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(int pos) {
        String s = airingTodays.get(pos).getTitle();
        Intent detailActivity = new Intent(getActivity(), DetailActivityTVShow.class);
        detailActivity.putExtra("ID", airingTodays.get(pos).getId());
        detailActivity.putExtra("TITLE", airingTodays.get(pos).getTitle());
        startActivity(detailActivity);
//        detailActivity.putExtra("IMG_URL", airingTodays.get(pos).getImgUrl());
//        detailActivity.putExtra("FIRST_AIR", airingTodays.get(pos).getFirstAirDate());
//        detailActivity.putExtra("RATING", airingTodays.get(pos).getRatings());
//        detailActivity.putExtra("OVERVIEW", airingTodays.get(pos).getOverview());

    }
}
