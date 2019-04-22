package com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.oliverstudio.developersandroidplayer.data.model.Video;

import java.util.List;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface VideosView extends MvpView {
    //TODO Use Moxy Strategy annotation. It is very useful! https://habr.com/ru/company/redmadrobot/blog/325816/

    @StateStrategyType(value = SkipStrategy.class)
    void showProgressBar();

    @StateStrategyType(value = SkipStrategy.class)
    void hideProgressBar();

    @StateStrategyType(value = SkipStrategy.class)
    void showFooter();

    @StateStrategyType(value = SkipStrategy.class)
    void hideFooter();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void inflateVideos(List<Video> videos, String nextPageToken);
}