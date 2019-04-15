package com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.repository;

import com.oliverstudio.developersandroidplayer.App;
import com.oliverstudio.developersandroidplayer.data.db.VideoDatabase;
import com.oliverstudio.developersandroidplayer.data.db.VideoEntity;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.presenter.HistoryPresenter;

import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {

    private BackToPresenter mBackToPresenter;
    private VideoDatabase mVideoDatabase;

    public HistoryRepository(HistoryPresenter presenter) {
        mBackToPresenter = presenter;
        mVideoDatabase = App.getInstance().getDatabase();
    }

    public void getHistoryWatchedFromDB() {
        List<VideoEntity> videoEntityList = mVideoDatabase.daoAccess().fetchAllVideos();
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