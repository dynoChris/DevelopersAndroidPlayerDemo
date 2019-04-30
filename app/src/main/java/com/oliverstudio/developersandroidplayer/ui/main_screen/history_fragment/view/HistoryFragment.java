package com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.view;

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
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.data.models.Video;
import com.oliverstudio.developersandroidplayer.data.network.ApiYoutube;
import com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.presenter.HistoryPresenter;
import com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.view.adapters.AdapterCallback;
import com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.view.adapters.HistoryRecyclerAdapter;
import com.oliverstudio.developersandroidplayer.utils.androidx_moxy.MvpAppCompatFragment;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends MvpAppCompatFragment implements HistoryView, AdapterCallback {

    //tags
    public static final String FRAGMENT_TAG = "fragment_history";

    //views
    private ProgressBar mProgressBar;
    private RecyclerView mHistoryRecyclerView;
    private TextView mHaveNotHistoryTextView;

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
            if (mVideoList.size() > 0) hideProgressBar();
        }
    }

    private void initViews(View view) {
        mProgressBar = view.findViewById(R.id.progress_bar);
        mHistoryRecyclerView = view.findViewById(R.id.recycler_view);
        mHaveNotHistoryTextView = view.findViewById(R.id.havnt_history_tv);
    }

    private void initRecycler() {
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHistoryRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mHistoryRecyclerAdapter = new HistoryRecyclerAdapter(mVideoList, this);
        mHistoryRecyclerView.setAdapter(mHistoryRecyclerAdapter);
        mHistoryRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mHistoryRecyclerView.setVisibility(View.GONE);
        mHaveNotHistoryTextView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mHistoryRecyclerView.setVisibility(View.VISIBLE);
        mHaveNotHistoryTextView.setVisibility(View.GONE);
    }

    @Override
    public void showHaveNotElementsText() {
        mHaveNotHistoryTextView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mHistoryRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void inflateHistory(List<Video> videoList) {
        mVideoList.clear();
        mVideoList.addAll(videoList);
        mHistoryRecyclerAdapter.notifyDataSetChanged();
        if (videoList.size() == 0) mPresenter.showHaventElementsText();
    }

    @Override
    public void openVideo(int position) {
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(getActivity(),
                ApiYoutube.API_KEY_YOUTUBE, mVideoList.get(position).getIdVideo());
        startActivity(intent);
    }
}