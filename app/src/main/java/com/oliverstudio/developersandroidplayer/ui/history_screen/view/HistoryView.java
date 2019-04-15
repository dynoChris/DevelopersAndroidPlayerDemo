package com.oliverstudio.developersandroidplayer.ui.history_screen.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.oliverstudio.developersandroidplayer.data.model.Video;

import java.util.List;

//@StateStrategyType(SkipStrategy.class)
public interface HistoryView extends MvpView {
    void showProgressBar();
    void hideProgressBar();
    void inflateHistory(List<Video> videoList);
}
