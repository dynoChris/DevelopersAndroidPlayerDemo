package com.oliverstudio.developersandroidplayer.presentation.videos_screen.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.VideoViewHolder> {

    private List<Video> mVideos;

    public VideoRecyclerAdapter(List<Video> videos) {
        mVideos = videos;
    }

    @NonNull
    @Override
    public VideoRecyclerAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoRecyclerAdapter.VideoViewHolder holder, int position) {
        Picasso.get()
                .load(mVideos.get(position).getUrlImage())
                .placeholder(R.drawable.placeholder_lightgrey_16x9)
                .into(holder.thumbnailImageView);
        holder.titleTextView.setText(mVideos.get(position).getTitle());
        holder.timePostTextView.setText(mVideos.get(position).getTimePost());
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnailImageView;
        private TextView titleTextView;
        private TextView timePostTextView;

        VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnailImageView = itemView.findViewById(R.id.thumbnail_iv);
            titleTextView = itemView.findViewById(R.id.title_iv);
            timePostTextView = itemView.findViewById(R.id.time_post_tv);
        }
    }
}
