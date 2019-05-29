package com.oliverstudio.developersandroidplayer.presentation.main_screen.history_fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.oliverstudio.developersandroidplayer.data.models.Video;

import java.util.List;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface HistoryView extends MvpView {
    void showProgressBar();
    void hideProgressBar();
    void showHaveNotElementsText();
    void inflateHistory(List<Video> videoList);
}