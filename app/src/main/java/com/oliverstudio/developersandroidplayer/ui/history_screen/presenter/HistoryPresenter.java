package com.oliverstudio.developersandroidplayer.ui.history_screen.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.ui.history_screen.repository.BackToPresenter;
import com.oliverstudio.developersandroidplayer.ui.history_screen.repository.HistoryRepository;
import com.oliverstudio.developersandroidplayer.ui.history_screen.view.HistoryView;

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
}
