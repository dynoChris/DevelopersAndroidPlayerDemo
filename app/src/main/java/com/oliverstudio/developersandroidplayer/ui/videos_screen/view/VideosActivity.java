package com.oliverstudio.developersandroidplayer.ui.videos_screen.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.network.NetworkUtils;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.presenter.VideosPresenter;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.view.adapters.RecyclerToActivity;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.view.adapters.VideoRecyclerAdapter;
import com.oliverstudio.developersandroidplayer.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class VideosActivity extends MvpAppCompatActivity implements VideosView, RecyclerToActivity {

    //views
    private Toolbar mToolbar;
    private RecyclerView mVideoRecyclerView;
    private ProgressBar mProgressBar;

    //general vars
    @InjectPresenter
    VideosPresenter mPresenter;
    private List<Video> mVideoList = new ArrayList<>();
    private VideoRecyclerAdapter mVideoRecyclerAdapter;

    //rest vars
    private String mNextPageToken = "";
    private boolean mIsLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        initViews();

        initRecycler();

        if (savedInstanceState == null) {
            mPresenter.getVideos();
        }

        mVideoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isBottomReached = !mVideoRecyclerView.canScrollVertically(Utils.SCROLLING_DOWN);
                if (isBottomReached && !mIsLoading) {
                    mPresenter.getVideos(mNextPageToken);
                }
            }
        });

    }

    private void initRecycler() {
        mVideoRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        mVideoRecyclerAdapter = new VideoRecyclerAdapter(mVideoList, this);
        mVideoRecyclerView.setAdapter(mVideoRecyclerAdapter);
        mVideoRecyclerAdapter.notifyDataSetChanged();
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
    public void showFooter() {
        mIsLoading = true;
        mVideoList.add(null);
        mVideoRecyclerAdapter.notifyDataSetChanged();
        mVideoRecyclerView.scrollToPosition(mVideoList.size()-1);
    }

    @Override
    public void hideFooter() {
        mIsLoading = false;
        try {
            int lastIndex = mVideoList.size()-1;
            if (mVideoList.get(lastIndex) == null) {
                mVideoList.remove(lastIndex);
            }
        } catch (IndexOutOfBoundsException e) {
            //do nothing
        }
    }

    @Override
    public void inflateVideos(List<Video> videos, String nextPageToken) {
        mNextPageToken = nextPageToken;
        mVideoList.addAll(videos);
        mVideoRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void openVideo(int position) {
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(this,
                NetworkUtils.API_KEY_YOUTUBE, mVideoList.get(position).getIdVideo());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.history:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}