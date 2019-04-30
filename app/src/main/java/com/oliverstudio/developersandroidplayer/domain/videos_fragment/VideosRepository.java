package com.oliverstudio.developersandroidplayer.domain.videos_fragment;

import android.annotation.SuppressLint;

import com.oliverstudio.developersandroidplayer.App;
import com.oliverstudio.developersandroidplayer.data.db.VideoDatabase;
import com.oliverstudio.developersandroidplayer.data.db.VideoEntity;
import com.oliverstudio.developersandroidplayer.data.models.Video;
import com.oliverstudio.developersandroidplayer.data.network.ApiYoutube;
import com.oliverstudio.developersandroidplayer.data.network.response.list_videos.Item;
import com.oliverstudio.developersandroidplayer.data.network.response.list_videos.ListVideosResponse;
import com.oliverstudio.developersandroidplayer.data.network.response.list_videos.Thumbnails;
import com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.presenter.VideosPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class VideosRepository {

    @Inject
    ApiYoutube mApiService;
    @Inject
    VideoDatabase mDatabase;
    private BackToPresenter mBackToPresenter;

    public VideosRepository(VideosPresenter presenter) {
        App.getAppComponent().inject(this);
        mBackToPresenter = presenter;
    }

    @SuppressLint("CheckResult")
    public void getVideos(String nextPageToken) {
        mApiService.getVideos(ApiYoutube.DEVELOPERS_ANDROID_PLAYLIST,
                nextPageToken,
                ApiYoutube.RESULTS_PER_PAGE,
                ApiYoutube.INCLUDE_SNIPPET,
                ApiYoutube.API_KEY_YOUTUBE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ListVideosResponse>() {
                    @Override
                    public void accept(ListVideosResponse listVideosResponse) throws Exception {
                        String nextPageToken = listVideosResponse.getNextPageToken();
                        List<Video> videos = getListVideos(listVideosResponse.getItems());
                        mBackToPresenter.onSuccess(videos, nextPageToken);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mBackToPresenter.onFailure();
                    }
                });

    }

    private List<Video> getListVideos(List<Item> items) {
        List<Video> videos = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            String idVideo = items.get(i).getSnippet().getResourceId().getVideoId();
            String urlImage = getUrlPicture(items.get(i).getSnippet().getThumbnails());
            String title = items.get(i).getSnippet().getTitle();
            String timePost = items.get(i).getSnippet().getPublishedAt();
            videos.add(new Video(idVideo, urlImage, title, timePost));
        }
        return videos;
    }

    private String getUrlPicture(Thumbnails thumbnails) {

//        if (thumbnails.getMaxres() != null) {
//            return thumbnails.getMaxres().getUrl();
//        }

        if (thumbnails.getStandard() != null) {
            return thumbnails.getStandard().getUrl();
        }
        if (thumbnails.getHigh() != null) {
            return thumbnails.getHigh().getUrl();
        }
        if (thumbnails.getMedium() != null) {
            return thumbnails.getMedium().getUrl();
        }
        return "";
    }

    public void insertVideo(Video video) {
        String idVideo = video.getIdVideo();
        String urlImage = video.getUrlImage();
        String title = video.getTitle();
        String timePost = video.getTimePost();

        final VideoEntity videoEntity = new VideoEntity(idVideo, urlImage, title, timePost);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mDatabase.daoAccess().insertVideo(videoEntity);
            }
        }).start();
    }
}