package com.oliverstudio.developersandroidplayer.presentation.list_videos_screen;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.presentation.list_videos_screen.adapters.VideoRecyclerAdapter;
import com.oliverstudio.developersandroidplayer.presentation.list_videos_screen.arch.ListVideosPresenter;
import com.oliverstudio.developersandroidplayer.presentation.list_videos_screen.arch.ListVideosView;


import java.util.ArrayList;
import java.util.List;

public class ListVideosActivity extends MvpAppCompatActivity implements ListVideosView {

    //views
    private RecyclerView mVideoRecyclerView;
    private ProgressBar mProgressBar;

    //general vars
    @InjectPresenter
    ListVideosPresenter mPresenter;
    private List<Video> mVideos = new ArrayList<>();

    //minor vars
    private String mNextPageToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_videos);

        initViews();

        initRecycler();

        mPresenter.getVideos(mNextPageToken);

    }

    private void initRecycler() {
        mVideoRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        VideoRecyclerAdapter videoRecyclerAdapter = new VideoRecyclerAdapter(mVideos);
        mVideoRecyclerView.setAdapter(videoRecyclerAdapter);
        videoRecyclerAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        mVideoRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mVideoRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mVideoRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void inflateVideos(List<Video> videos, String nextPageToken) {
        mNextPageToken = nextPageToken;
        mVideos.addAll(videos);
    }
}