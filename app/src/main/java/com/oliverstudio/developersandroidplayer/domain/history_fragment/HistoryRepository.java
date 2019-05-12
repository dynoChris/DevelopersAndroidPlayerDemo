package com.oliverstudio.developersandroidplayer.domain.history_fragment;

import android.annotation.SuppressLint;

import com.oliverstudio.developersandroidplayer.App;
import com.oliverstudio.developersandroidplayer.data.db.VideoDatabase;
import com.oliverstudio.developersandroidplayer.data.db.VideoEntity;
import com.oliverstudio.developersandroidplayer.data.models.Video;
import com.oliverstudio.developersandroidplayer.presentation.main_screen.history_fragment.presenter.HistoryPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
                .map(new Function<List<VideoEntity>, List<Video>>() {
                    @Override
                    public List<Video> apply(List<VideoEntity> videoEntities) throws Exception {
                        return getVideoList(videoEntities);
                    }
                })
                .subscribe(new Consumer<List<Video>>() {
                    @Override
                    public void accept(List<Video> videos) throws Exception {
                        mBackToPresenter.onSuccess(videos);
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