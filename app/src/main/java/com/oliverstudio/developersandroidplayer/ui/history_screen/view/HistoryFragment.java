package com.oliverstudio.developersandroidplayer.ui.history_screen.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.network.NetworkUtils;
import com.oliverstudio.developersandroidplayer.ui.history_screen.presenter.HistoryPresenter;
import com.oliverstudio.developersandroidplayer.ui.history_screen.view.adapters.HistoryRecyclerAdapter;
import com.oliverstudio.developersandroidplayer.ui.history_screen.view.adapters.RecyclerToFragment;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends MvpAppCompatFragment implements HistoryView, RecyclerToFragment {

    //tags
    public static final String FRAGMENT_TAG = "fragment_history";

    //views
    private ProgressBar mProgressBar;
    private RecyclerView mHistoryRecyclerView;

    //general vars
    @InjectPresenter
    HistoryPresenter mPresenter;
    private List<Video> mVideoList = new ArrayList<>();
    private HistoryRecyclerAdapter mHistoryRecyclerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecycler();

        if (savedInstanceState == null) {
            mPresenter.getHistoryWatchedFromDB();
        } else {
            hideProgressBar();
        }

    }

    private void initRecycler() {
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mHistoryRecyclerAdapter = new HistoryRecyclerAdapter(mVideoList, this);
        mHistoryRecyclerView.setAdapter(mHistoryRecyclerAdapter);
        mHistoryRecyclerAdapter.notifyDataSetChanged();
    }

    private void initViews(View view) {
        mProgressBar = view.findViewById(R.id.progress_bar);
        mHistoryRecyclerView = view.findViewById(R.id.recycler_view);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mHistoryRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mHistoryRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void inflateHistory(List<Video> videoList) {
        mVideoList.clear();
        mVideoList.addAll(videoList);
        mHistoryRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void openVideo(int position) {
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(getActivity(),
                NetworkUtils.API_KEY_YOUTUBE, mVideoList.get(position).getIdVideo());
        startActivity(intent);
    }
}
