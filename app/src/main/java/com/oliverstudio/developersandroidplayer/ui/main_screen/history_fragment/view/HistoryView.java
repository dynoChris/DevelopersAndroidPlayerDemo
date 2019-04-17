package com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.oliverstudio.developersandroidplayer.data.model.Video;

import java.util.List;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface HistoryView extends MvpView {
    void showProgressBar();
    void hideProgressBar();
    void inflateHistory(List<Video> videoList);
    void showHaventElementsText();
}