package com.oliverstudio.developersandroidplayer.presentation.videos_screen;

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
import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.presentation.videos_screen.adapters.RecyclerToActivity;
import com.oliverstudio.developersandroidplayer.presentation.videos_screen.adapters.VideoRecyclerAdapter;
import com.oliverstudio.developersandroidplayer.presentation.videos_screen.arch.VideosPresenter;
import com.oliverstudio.developersandroidplayer.presentation.videos_screen.arch.VideosView;

import java.util.ArrayList;
import java.util.List;

public class VideosActivity extends MvpAppCompatActivity implements VideosView, RecyclerToActivity {

    //tags
    private static final String NEXT_PAGE_TOKEN_TAG = "next_page_token";

    //views
    private Toolbar mToolbar;
    private RecyclerView mVideoRecyclerView;
    private ProgressBar mProgressBar;

    //general vars
    @InjectPresenter
    VideosPresenter mPresenter;
    private List<Video> mVideos = new ArrayList<>();

    //rest vars
    private String mNextPageToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        initViews();

        initRecycler();

        if (savedInstanceState == null) {
            mPresenter.getVideos(mNextPageToken);
        }

    }

    private void initRecycler() {
        mVideoRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        VideoRecyclerAdapter videoRecyclerAdapter = new VideoRecyclerAdapter(mVideos, this);
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

    @Override
    public void openVideo(int position) {
        mPresenter.openVideo(mVideos.get(position).getIdVideo());
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