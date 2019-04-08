package com.oliverstudio.developersandroidplayer.presentation.videos_screen.arch;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.oliverstudio.developersandroidplayer.data.model.Video;
import com.oliverstudio.developersandroidplayer.network.NetworkUtils;
import com.oliverstudio.developersandroidplayer.presentation.videos_screen.VideosActivity;
import com.oliverstudio.developersandroidplayer.utils.Utils;

import java.util.List;

@InjectViewState
public class VideosPresenter extends MvpPresenter<VideosView> implements BackToPresenter {

    private VideosRepository mRepository;

    public VideosPresenter() {
        mRepository = new VideosRepository(this);
    }

    public void getVideos(String nextPageToken) {
        getViewState().showProgressBar();
        mRepository.getVideos(nextPageToken);
    }

    public void openVideo(String id, Activity activity) {
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(activity, NetworkUtils.API_KEY_YOUTUBE, id);
        activity.startActivity(intent);
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