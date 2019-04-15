package com.oliverstudio.developersandroidplayer.ui.videos_screen.view.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.view.VideosFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_PROGRESS = 1;

    private List<Video> mVideoList;
    private RecyclerToFragment mCallback;

    public VideoRecyclerAdapter(List<Video> videoList, VideosFragment fragment) {
        mVideoList = videoList;
        mCallback = fragment;
    }

    @Override
    public int getItemViewType(int position) {
        return mVideoList.get(position) == null ? VIEW_TYPE_PROGRESS : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
            return new VideoViewHolder(view);
        } else if (viewType == VIEW_TYPE_PROGRESS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);
            return new ProgressViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VideoViewHolder) {
            VideoViewHolder videoHolder = (VideoViewHolder) holder;
            Picasso.get()
                    .load(mVideoList.get(position).getUrlImage())
                    .placeholder(R.drawable.placeholder_lightgrey_16x9)
                    .into(videoHolder.thumbnailImageView);
            videoHolder.titleTextView.setText(mVideoList.get(position).getTitle());
            videoHolder.timePostTextView.setText(mVideoList.get(position).getTimePost());
            videoHolder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.insertVideoToDB(mVideoList.get(holder.getAdapterPosition()));
                    mCallback.openVideo(holder.getAdapterPosition());
                }
            });
        } else if (holder instanceof ProgressViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

}

class VideoViewHolder extends RecyclerView.ViewHolder {

    ConstraintLayout parent;
    ImageView thumbnailImageView;
    TextView titleTextView;
    TextView timePostTextView;

    VideoViewHolder(@NonNull View itemView) {
        super(itemView);

        parent = itemView.findViewById(R.id.video_container);
        thumbnailImageView = itemView.findViewById(R.id.thumbnail_iv);
        titleTextView = itemView.findViewById(R.id.title_iv);
        timePostTextView = itemView.findViewById(R.id.time_post_tv);
    }
}

class ProgressViewHolder extends RecyclerView.ViewHolder {

    ProgressBar mProgressBar;

    public ProgressViewHolder(@NonNull View itemView) {
        super(itemView);

        mProgressBar = itemView.findViewById(R.id.progress_bar);
    }
}