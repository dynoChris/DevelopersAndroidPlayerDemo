package com.oliverstudio.developersandroidplayer.presentation.list_videos_screen.arch.callbacks;

import com.oliverstudio.developersandroidplayer.data.model.Video;

import java.util.List;

public interface BackToPresenterCallback {

    void onSuccess(List<Video> videos, String nextPageToken);
    void onFailure();

}
