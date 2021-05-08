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
import com.example.moviecataloguetask.models.AiringToday;
import com.example.moviecataloguetask.networks.Const;

import java.util.List;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.ViewHolder> {
    private List<AiringToday> airingTodays;
    private OnItemClick onItemClick;

    public interface OnItemClick {
        void onClick(int pos);
    }

    public TVShowAdapter(List<AiringToday> airingTodays, OnItemClick onItemClick){
        this.airingTodays = airingTodays;
        this.onItemClick = onItemClick;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie, parent, false);
        return new ViewHolder(view, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(Const.IMG_URL_200+airingTodays.get(position).getImgUrl()).into(holder.ivPoster);
        holder.tvTitle.setText(airingTodays.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return airingTodays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClick onItemClick;
        ImageView ivPoster;
        TextView tvTitle;


        public ViewHolder(@NonNull View itemView, OnItemClick onItemClick) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            this.onItemClick = onItemClick;
        }


        @Override
        public void onClick(View v) {
            onItemClick.onClick(getAdapterPosition());
        }
    }
}
