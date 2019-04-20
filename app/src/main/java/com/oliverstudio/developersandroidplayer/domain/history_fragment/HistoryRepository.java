package com.oliverstudio.developersandroidplayer.domain.history_fragment;

import com.oliverstudio.developersandroidplayer.App;
import com.oliverstudio.developersandroidplayer.data.db.VideoDatabase;
import com.oliverstudio.developersandroidplayer.data.db.VideoEntity;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.presenter.HistoryPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HistoryRepository {

    @Inject
    VideoDatabase mDatabase;
    private BackToPresenter mBackToPresenter;

    public HistoryRepository(HistoryPresenter presenter) {
        App.getAppComponent().inject(this);
        mBackToPresenter = presenter;
    }

    public void getHistoryWatchedFromDB() {
        List<VideoEntity> videoEntityList = mDatabase.daoAccess().fetchAllVideos();
        List<Video> videoList = new ArrayList<>();
        for (int i = 0; i < videoEntityList.size(); i++) {
            String idVideo = videoEntityList.get(i).getIdVideo();
            String urlImage = videoEntityList.get(i).getUrlImage();
            String title = videoEntityList.get(i).getTitle();
            String timePost = videoEntityList.get(i).getTimePost();
            Video video = new Video(idVideo, urlImage, title, timePost);
            videoList.add(video);
        }
        mBackToPresenter.onSuccess(videoList);
    }
}