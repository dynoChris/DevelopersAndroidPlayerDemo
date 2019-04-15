package com.oliverstudio.developersandroidplayer.ui.history_screen.view.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.ui.history_screen.view.HistoryFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryViewHolder> {

    private List<Video> mVideoList;
    private RecyclerToFragment mCallback;

    public HistoryRecyclerAdapter(List<Video> videoList, HistoryFragment fragment) {
        mVideoList = videoList;
        mCallback = fragment;
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
        Picasso.get()
                .load(mVideoList.get(position).getUrlImage())
                .placeholder(R.drawable.placeholder_lightgrey_16x9)
                .into(holder.thumbnailImageView);
        holder.titleTextView.setText(mVideoList.get(position).getTitle());
        holder.timePostTextView.setText(mVideoList.get(position).getTimePost());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.openVideo(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout parent;
        ImageView thumbnailImageView;
        TextView titleTextView;
        TextView timePostTextView;

        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.video_container);
            thumbnailImageView = itemView.findViewById(R.id.thumbnail_iv);
            titleTextView = itemView.findViewById(R.id.title_iv);
            timePostTextView = itemView.findViewById(R.id.time_post_tv);
        }
    }
}
