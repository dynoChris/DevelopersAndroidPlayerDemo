package com.oliverstudio.developersandroidplayer.presentation.main_screen.history_fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.oliverstudio.developersandroidplayer.data.models.Video;
import com.oliverstudio.developersandroidplayer.domain.history_fragment.BackToPresenter;
import com.oliverstudio.developersandroidplayer.domain.history_fragment.HistoryRepository;
import com.oliverstudio.developersandroidplayer.presentation.main_screen.history_fragment.HistoryView;

import java.util.List;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> implements BackToPresenter {

    private HistoryRepository mRepository;

    public HistoryPresenter() {
        mRepository = new HistoryRepository(this);
    }

    public void getHistoryWatchedFromDB() {
        getViewState().showProgressBar();
        mRepository.getHistoryWatchedFromDB();
    }

    @Override
    public void onSuccess(List<Video> videoList) {
        getViewState().hideProgressBar();
        getViewState().inflateHistory(videoList);
    }

    public void showHaventElementsText() {
        getViewState().showHaveNotElementsText();
    }
}