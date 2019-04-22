package com.oliverstudio.developersandroidplayer.domain.videos_fragment;

import android.util.Log;

import com.oliverstudio.developersandroidplayer.App;
import com.oliverstudio.developersandroidplayer.data.db.VideoDatabase;
import com.oliverstudio.developersandroidplayer.data.db.VideoEntity;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.domain.ExceptionsKt;
import com.oliverstudio.developersandroidplayer.network.ApiYoutube;
import com.oliverstudio.developersandroidplayer.network.response.list_videos.Item;
import com.oliverstudio.developersandroidplayer.network.response.list_videos.ListVideosResponse;
import com.oliverstudio.developersandroidplayer.network.response.list_videos.Thumbnails;
import com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.presenter.VideosPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideosRepository {

    private final String TAG = this.getClass().getSimpleName();

    @Inject
    ApiYoutube mApiService;
    @Inject
    VideoDatabase mDatabase;
    private BackToPresenter mBackToPresenter;

    public VideosRepository(VideosPresenter presenter) {
        App.getAppComponent().inject(this);
        mBackToPresenter = presenter;
    }

    public void getVideos(String nextPageToken) {
        Call<ListVideosResponse> call = mApiService.getVideos(
                ApiYoutube.DEVELOPERS_ANDROID_PLAYLIST,
                nextPageToken,
                ApiYoutube.RESULTS_PER_PAGE,
                ApiYoutube.INCLUDE_SNIPPET,
                ApiYoutube.API_KEY_YOUTUBE);

        call.enqueue(new Callback<ListVideosResponse>() {
            @Override
            public void onResponse(Call<ListVideosResponse> call, Response<ListVideosResponse> response) {

                if (response.isSuccessful() &&
                        /*TODO always check null here!*/response.body() != null
                ) {
                    String nextPageToken = response.body().getNextPageToken();
                    List<Video> videos = getListVideos(response.body().getItems());
                    mBackToPresenter.onSuccess(videos, nextPageToken);
                }
            }

            @Override
            public void onFailure(Call<ListVideosResponse> call, Throwable t) {
                //TODO check what wrong if result failed.
                String message = ExceptionsKt.getExceptionMessage(t);
                Log.e(TAG,
                        " Failed to load videos" + t.getMessage()
                                + "I think, it is something like: " + message
                );

                if (ExceptionsKt.isNoNetwork(t)) {
                    //Show no network if user has no internet connection on phone!
                    mBackToPresenter.onFailure(message);
                }
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

        /***
         *
         * TODO: Note! With Java 8 features You can simplify some code via lambda. It is easy. Android Studio will help you!
         *  new Thread(new Runnable() {
         *             @Override
         *             public void run() {
         *                 mDatabase.daoAccess().insertVideo(videoEntity);
         *             }
         *         }).start();
         *
         */
        new Thread(() -> mDatabase.daoAccess().insertVideo(videoEntity)).start();
    }
}