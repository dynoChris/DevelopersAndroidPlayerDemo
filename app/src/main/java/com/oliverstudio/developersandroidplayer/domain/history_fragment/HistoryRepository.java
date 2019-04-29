package com.oliverstudio.developersandroidplayer.domain.history_fragment;

import android.annotation.SuppressLint;

import com.oliverstudio.developersandroidplayer.App;
import com.oliverstudio.developersandroidplayer.data.db.VideoDatabase;
import com.oliverstudio.developersandroidplayer.data.db.VideoEntity;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.presenter.HistoryPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HistoryRepository {

    @Inject
    VideoDatabase mDatabase;
    private BackToPresenter mBackToPresenter;

    public HistoryRepository(HistoryPresenter presenter) {
        App.getAppComponent().inject(this);
        mBackToPresenter = presenter;
    }

    @SuppressLint("CheckResult")
    public void getHistoryWatchedFromDB() {
        mDatabase.daoAccess().fetchAllVideos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<VideoEntity>>() {
                    @Override
                    public void accept(List<VideoEntity> videoEntities) throws Exception {
                        List<Video> videoList = getVideoList(videoEntities);
                        mBackToPresenter.onSuccess(videoList);
                    }
                });
    }

    private List<Video> getVideoList(List<VideoEntity> videoEntities) {
        List<Video> videoList = new ArrayList<>();
        for (int i = 0; i < videoEntities.size(); i++) {
            String idVideo = videoEntities.get(i).getIdVideo();
            String urlImage = videoEntities.get(i).getUrlImage();
            String title = videoEntities.get(i).getTitle();
            String timePost = videoEntities.get(i).getTimePost();
            Video video = new Video(idVideo, urlImage, title, timePost);
            videoList.add(video);
        }
        return videoList;
    }
}