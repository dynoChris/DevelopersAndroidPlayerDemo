package com.oliverstudio.developersandroidplayer.ui.videos_screen.view;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.db.StorageUtils;
import com.oliverstudio.developersandroidplayer.data.db.VideoDatabase;
import com.oliverstudio.developersandroidplayer.data.db.VideoEntity;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.network.NetworkUtils;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.presenter.VideosPresenter;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.view.adapters.RecyclerToFragment;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.view.adapters.VideoRecyclerAdapter;
import com.oliverstudio.developersandroidplayer.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class VideosFragment extends MvpAppCompatFragment implements VideosView, RecyclerToFragment {

    //tags
    public static final String FRAGMENT_TAG = "fragment_videos";

    //views
    private RecyclerView mVideoRecyclerView;
    private ProgressBar mProgressBar;

    //general vars
    @InjectPresenter
    VideosPresenter mPresenter;
    private VideoDatabase mVideoDatabase;
    private List<Video> mVideoList = new ArrayList<>();
    private VideoRecyclerAdapter mVideoRecyclerAdapter;

    //vars
    private String mNextPageToken = "";
    private boolean mIsLoading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        initRecycler();

        mVideoDatabase = Room.databaseBuilder(getContext(), VideoDatabase.class, StorageUtils.DATABASE_NAME)
                .build();

        if (savedInstanceState == null) {
            mPresenter.getVideos();
        } else {
            hideProgressBar();
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            Log.d(Utils.TAG, "onActivityCreated: ");
        }
    }

    private void initViews(View view) {
        mVideoRecyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);
    }

    private void initRecycler() {
        mVideoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mVideoRecyclerAdapter = new VideoRecyclerAdapter(mVideoList, this);
        mVideoRecyclerView.setAdapter(mVideoRecyclerAdapter);
        mVideoRecyclerAdapter.notifyDataSetChanged();
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
    public void insertVideoToDB(Video video) {
        String idVideo = video.getIdVideo();
        String urlImage = video.getUrlImage();
        String title = video.getTitle();
        String timePost = video.getTimePost();

        final VideoEntity videoEntity = new VideoEntity(idVideo, urlImage, title, timePost);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mVideoDatabase.daoAccess().insertVideo(videoEntity);
            }
        }).start();

    }

    @Override
    public void openVideo(int position) {
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(getActivity(),
                NetworkUtils.API_KEY_YOUTUBE, mVideoList.get(position).getIdVideo());
        startActivity(intent);
    }
}
