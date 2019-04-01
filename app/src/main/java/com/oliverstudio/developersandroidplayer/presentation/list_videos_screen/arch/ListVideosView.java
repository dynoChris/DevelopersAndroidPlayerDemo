package com.oliverstudio.developersandroidplayer.presentation.list_videos_screen.arch;

import com.arellomobile.mvp.MvpView;
import com.oliverstudio.developersandroidplayer.data.model.Video;

import java.util.List;

public interface ListVideosView extends MvpView {

    void showProgressBar();
    void hideProgressBar();
    void inflateVideos(List<Video> videos, String nextPageToken);

}