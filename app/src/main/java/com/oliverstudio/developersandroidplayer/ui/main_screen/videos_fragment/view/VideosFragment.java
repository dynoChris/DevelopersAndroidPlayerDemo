package com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.network.ApiYoutube;
import com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.presenter.VideosPresenter;
import com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.view.adapters.AdapterCallback;
import com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.view.adapters.VideoRecyclerAdapter;
import com.oliverstudio.developersandroidplayer.utils.Utils;
import com.oliverstudio.developersandroidplayer.utils.androidx_moxy.MvpAppCompatFragment;

import java.util.ArrayList;
import java.util.List;

public class VideosFragment extends MvpAppCompatFragment implements VideosView, AdapterCallback {

    //tags
    public static final String FRAGMENT_TAG = "fragment_videos";

    //views
    private ProgressBar mProgressBar;
    private RecyclerView mVideoRecyclerView;

    //general vars
    @InjectPresenter
    VideosPresenter mPresenter;
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

        initRecycler();

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

    private void initViews(View view) {
        mVideoRecyclerView = view.findViewById(R.id.recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);
    }

    private void initRecycler() {
        mVideoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mVideoRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
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
        mVideoRecyclerView.scrollToPosition(mVideoList.size() - 1);
    }

    @Override
    public void hideFooter() {
        mIsLoading = false;
        try {
            int lastIndex = mVideoList.size() - 1;
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
        mPresenter.insertVideo(video);
    }

    @Override
    public void openVideo(int position) {
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(getActivity(),
                ApiYoutube.API_KEY_YOUTUBE, mVideoList.get(position).getIdVideo());
        startActivity(intent);
    }
}
