package com.oliverstudio.developersandroidplayer.ui.videos_screen.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.repository.BackToPresenter;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.repository.VideosRepository;
import com.oliverstudio.developersandroidplayer.ui.videos_screen.view.VideosView;

import java.util.List;

@InjectViewState
public class VideosPresenter extends MvpPresenter<VideosView> implements BackToPresenter {

    private VideosRepository mRepository;

    public VideosPresenter() {
        mRepository = new VideosRepository(this);
    }

    public void getVideos() {
        mRepository.getVideos();
    }

    public void getVideos(String nextPageToken) {
        getViewState().showFooter();
        mRepository.getVideos(nextPageToken);
    }

    @Override
    public void onSuccess(List<Video> videos, String nextPageToken) {
        getViewState().hideProgressBar();
        getViewState().hideFooter();
        getViewState().inflateVideos(videos, nextPageToken);
    }

    @Override
    public void onFailure() {
        getViewState().hideProgressBar();
    }

    public void insertVideo(Video video) {
        mRepository.insertVideo(video);
    }
}