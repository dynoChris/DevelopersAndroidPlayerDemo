package com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.view.adapters;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.models.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_ITEM = 0;
    public static final int VIEW_TYPE_PROGRESS = 1;

    private List<Video> mVideoList;
    private AdapterCallback mCallback;

    public VideoRecyclerAdapter(List<Video> videoList, AdapterCallback callback) {
        mVideoList = videoList;
        mCallback = callback;
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
            ((VideoViewHolder) holder).bind(mVideoList.get(position), mCallback);
        } else if (holder instanceof ProgressViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

}

class VideoViewHolder extends RecyclerView.ViewHolder {

    ConstraintLayout mParent;
    ImageView mThumbnailImageView;
    TextView mTitleTextView;
    TextView mTimePostTextView;

    VideoViewHolder(@NonNull View itemView) {
        super(itemView);

        mParent = itemView.findViewById(R.id.video_container);
        mThumbnailImageView = itemView.findViewById(R.id.thumbnail_iv);
        mTitleTextView = itemView.findViewById(R.id.title_iv);
        mTimePostTextView = itemView.findViewById(R.id.time_post_tv);
    }

    public void bind(final Video video, final AdapterCallback callback) {
        Picasso.get()
                .load(video.getUrlImage())
                .placeholder(R.drawable.placeholder_lightgrey_16x9)
                .into(mThumbnailImageView);
        mTitleTextView.setText(video.getTitle());
        mTimePostTextView.setText(video.getTimePost());
        mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.insertVideoToDB(video);
                callback.openVideo(getAdapterPosition());
            }
        });
    }
}

class ProgressViewHolder extends RecyclerView.ViewHolder {

    ProgressBar mProgressBar;

    public ProgressViewHolder(@NonNull View itemView) {
        super(itemView);

        mProgressBar = itemView.findViewById(R.id.progress_bar);
    }

}