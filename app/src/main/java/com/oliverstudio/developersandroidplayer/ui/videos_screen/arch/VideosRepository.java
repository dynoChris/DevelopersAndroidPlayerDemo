package com.oliverstudio.developersandroidplayer.ui.videos_screen.arch;

import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.network.ApiService;
import com.oliverstudio.developersandroidplayer.network.NetworkUtils;
import com.oliverstudio.developersandroidplayer.network.response.list_videos.Item;
import com.oliverstudio.developersandroidplayer.network.response.list_videos.ListVideosResponse;
import com.oliverstudio.developersandroidplayer.network.response.list_videos.Thumbnails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideosRepository {

    private ApiService mApiService;
    private BackToPresenter mBackToPresenter;

    public VideosRepository(VideosPresenter presenter) {
        mApiService = NetworkUtils.getApiService();
        mBackToPresenter = presenter;
    }

    public void getVideos() {
        Call<ListVideosResponse> call = mApiService.getVideos(
                NetworkUtils.DEVELOPERS_ANDROID_PLAYLIST,
                "",
                NetworkUtils.RESULTS_PER_PAGE,
                NetworkUtils.INCLUDE_SNIPPET,
                NetworkUtils.API_KEY_YOUTUBE);

        call.enqueue(new Callback<ListVideosResponse>() {
            @Override
            public void onResponse(Call<ListVideosResponse> call, Response<ListVideosResponse> response) {
                if (response.isSuccessful()) {
                    String nextPageToken = response.body().getNextPageToken();
                    List<Video> videos = getListVideos(response.body().getItems());
                    mBackToPresenter.onSuccess(videos, nextPageToken);
                }
            }

            @Override
            public void onFailure(Call<ListVideosResponse> call, Throwable t) {
                mBackToPresenter.onFailure();
            }
        });
    }

    public void getVideos(String nextPageToken) {
        Call<ListVideosResponse> call = mApiService.getVideos(
                NetworkUtils.DEVELOPERS_ANDROID_PLAYLIST,
                nextPageToken,
                NetworkUtils.RESULTS_PER_PAGE,
                NetworkUtils.INCLUDE_SNIPPET,
                NetworkUtils.API_KEY_YOUTUBE);

        call.enqueue(new Callback<ListVideosResponse>() {
            @Override
            public void onResponse(Call<ListVideosResponse> call, Response<ListVideosResponse> response) {
                if (response.isSuccessful()) {
                    String nextPageToken = response.body().getNextPageToken();
                    List<Video> videos = getListVideos(response.body().getItems());
                    mBackToPresenter.onSuccess(videos, nextPageToken);
                }
            }

            @Override
            public void onFailure(Call<ListVideosResponse> call, Throwable t) {
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
}