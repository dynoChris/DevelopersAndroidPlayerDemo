package com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.repository.BackToPresenter;
import com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.repository.HistoryRepository;
import com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.view.HistoryView;

import java.util.List;

@InjectViewState
public class HistoryPresenter extends MvpPresenter<HistoryView> implements BackToPresenter {

    private HistoryRepository mRepository;

    public HistoryPresenter() {
        mRepository = new HistoryRepository(this);
    }

    public void getHistoryWatchedFromDB() {
        mRepository.getHistoryWatchedFromDB();
    }

    @Override
    public void onSuccess(List<Video> videoList) {
        getViewState().hideProgressBar();
        getViewState().inflateHistory(videoList);
    }

    public void showHaventElementsText() {
        getViewState().showHaventElementsText();
    }
}