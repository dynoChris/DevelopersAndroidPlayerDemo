package com.oliverstudio.developersandroidplayer.presentation.list_videos_screen.arch;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.presentation.list_videos_screen.arch.callbacks.BackToPresenterCallback;
import com.oliverstudio.developersandroidplayer.presentation.list_videos_screen.arch.callbacks.PresenterToRepositoryCallback;
import com.oliverstudio.developersandroidplayer.utils.Utils;

import java.util.List;

@InjectViewState
public class ListVideosPresenter extends MvpPresenter<ListVideosView> implements BackToPresenterCallback {

    private PresenterToRepositoryCallback mRepository;

    public ListVideosPresenter() {
        mRepository = new ListVideosRepository(this);
    }

    public void getVideos(String nextPageToken) {
        getViewState().showProgressBar();
        mRepository.getVideos(nextPageToken);
    }

    @Override
    public void onSuccess(List<Video> videos, String nextPageToken) {
        getViewState().hideProgressBar();
        getViewState().inflateVideos(videos, nextPageToken);
    }

    @Override
    public void onFailure() {
        getViewState().hideProgressBar();
        Log.d(Utils.TAG, "onSuccess: ");
    }
}