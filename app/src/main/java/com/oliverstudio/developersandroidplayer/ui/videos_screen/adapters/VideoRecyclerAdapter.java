package com.oliverstudio.developersandroidplayer.ui.videos_screen.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.VideosActivity;
import com.oliverstudio.developersandroidplayer.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.VideoViewHolder> {

    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_LOADING = 1;

    private RecyclerToActivity mCallback;
    private List<Video> mVideos;

    public VideoRecyclerAdapter(List<Video> videos, VideosActivity activity) {
        mVideos = videos;
        mCallback = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return mVideos.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public VideoRecyclerAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            Log.d(Utils.TAG, "onCreateViewHolder: ");
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoRecyclerAdapter.VideoViewHolder holder, final int position) {
        Picasso.get()
                .load(mVideos.get(position).getUrlImage())
                .placeholder(R.drawable.placeholder_lightgrey_16x9)
                .into(holder.thumbnailImageView);
        holder.titleTextView.setText(mVideos.get(position).getTitle());
        holder.timePostTextView.setText(mVideos.get(position).getTimePost());

        holder.thumbnailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.openVideo(position);
            }
        });
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
