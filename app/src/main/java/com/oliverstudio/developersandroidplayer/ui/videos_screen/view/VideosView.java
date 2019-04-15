package com.oliverstudio.developersandroidplayer.ui.videos_screen.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.oliverstudio.developersandroidplayer.data.model.Video;

import java.util.List;

public interface VideosView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showProgressBar();
    @StateStrategyType(SkipStrategy.class)
    void hideProgressBar();
    @StateStrategyType(SkipStrategy.class)
    void showFooter();
    @StateStrategyType(SkipStrategy.class)
    void hideFooter();
    @StateStrategyType(SkipStrategy.class)
    void inflateVideos(List<Video> videos, String nextPageToken);

}