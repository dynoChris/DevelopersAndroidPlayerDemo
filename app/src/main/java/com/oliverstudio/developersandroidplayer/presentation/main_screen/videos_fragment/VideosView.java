package com.oliverstudio.developersandroidplayer.presentation.main_screen.videos_fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.oliverstudio.developersandroidplayer.data.models.Video;

import java.util.List;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface VideosView extends MvpView {
    void showProgressBar();
    void hideProgressBar();
    void showFooter();
    void hideFooter();
    void inflateVideos(List<Video> videos, String nextPageToken);
}