package com.oliverstudio.developersandroidplayer.presentation.main_screen.history_fragment.view.adapters;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.models.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryViewHolder> {

    private List<Video> mVideoList;
    private AdapterCallback mCallback;

    public HistoryRecyclerAdapter(List<Video> videoList, AdapterCallback callback) {
        mVideoList = videoList;
        mCallback = callback;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryViewHolder holder, int position) {
        holder.bind(mVideoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout mParent;
        ImageView mThumbnailImageView;
        TextView mTitleTextView;
        TextView mTimePostTextView;

        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            mParent = itemView.findViewById(R.id.video_container);
            mThumbnailImageView = itemView.findViewById(R.id.thumbnail_iv);
            mTitleTextView = itemView.findViewById(R.id.title_iv);
            mTimePostTextView = itemView.findViewById(R.id.time_post_tv);
        }

        public void bind(Video video) {
            Picasso.get()
                    .load(video.getUrlImage())
                    .placeholder(R.drawable.placeholder_lightgrey_16x9)
                    .into(mThumbnailImageView);
            mTitleTextView.setText(video.getTitle());
            mTimePostTextView.setText(video.getTimePost());
            mParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.openVideo(getAdapterPosition());
                }
            });
        }
    }
}