package com.oliverstudio.developersandroidplayer.presentation.videos_screen.arch;

import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.network.ApiService;
import com.oliverstudio.developersandroidplayer.network.NetworkUtils;
import com.oliverstudio.developersandroidplayer.network.response.list_videos.Item;
import com.oliverstudio.developersandroidplayer.network.response.list_videos.ListVideosResponse;
import com.oliverstudio.developersandroidplayer.presentation.videos_screen.arch.callbacks.BackToPresenterCallback;
import com.oliverstudio.developersandroidplayer.presentation.videos_screen.arch.callbacks.PresenterToRepositoryCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideosRepository implements PresenterToRepositoryCallback {

    private ApiService mApiService;
    private BackToPresenterCallback mBackToPresenterCallback;

    public VideosRepository(VideosPresenter presenter) {
        mApiService = NetworkUtils.getApiService();
        mBackToPresenterCallback = presenter;
    }

    @Override
    public void getVideos(String nextPageToken) {
        Call<ListVideosResponse> call = mApiService.getVideos(
                NetworkUtils.DEVELOPERS_ANDROID_PLAYLIST,
                nextPageToken,
                NetworkUtils.RESULTS_PER_PAGE,
                NetworkUtils.INCLUDE_SNIPPET,
                NetworkUtils.API_KEY);

        call.enqueue(new Callback<ListVideosResponse>() {
            @Override
            public void onResponse(Call<ListVideosResponse> call, Response<ListVideosResponse> response) {
                if (response.isSuccessful()) {
                    String nextPageToken = response.body().getNextPageToken();
                    List<Video> videos = getListVideos(response.body().getItems());
                    mBackToPresenterCallback.onSuccess(videos, nextPageToken);
                }
            }

            @Override
            public void onFailure(Call<ListVideosResponse> call, Throwable t) {
                mBackToPresenterCallback.onFailure();
            }
        });
    }

    private List<Video> getListVideos(List<Item> items) {
        List<Video> videos = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            String urlImage = items.get(i).getSnippet().getThumbnails().getStandard().getUrl();
            String title = items.get(i).getSnippet().getTitle();
            String timePost = items.get(i).getSnippet().getPublishedAt();
            videos.add(new Video(urlImage, title, timePost));
        }
        return videos;
    }
}