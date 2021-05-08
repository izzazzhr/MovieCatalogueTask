package com.example.moviecataloguetask.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviecataloguetask.R;
import com.example.moviecataloguetask.models.NowPlaying;
import com.example.moviecataloguetask.networks.Const;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {
    private List<NowPlaying> nowPlayings;
    private OnItemClick onItemClick;

    public NowPlayingAdapter(List<NowPlaying> nowPlayings, OnItemClick onItemClick) {
        this.nowPlayings = nowPlayings;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_movie1, viewGroup, false);
        return new ViewHolder(view, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(viewHolder.itemView.getContext())
                .load(Const.IMG_URL_200 + nowPlayings.get(i).getImgUrl())
                .into(viewHolder.ivPoster);
        viewHolder.tvTitle.setText(nowPlayings.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return nowPlayings.size();
    }

    public interface OnItemClick {
        void onClick(int pos);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClick onItemClick;
        ImageView ivPoster;
        TextView tvTitle;

        ViewHolder(@NonNull View itemView, OnItemClick onItemClick) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            this.onItemClick = onItemClick;
        }

        @Override
        public void onClick(View view) {
            onItemClick.onClick(getAdapterPosition());
        }
    }
}
