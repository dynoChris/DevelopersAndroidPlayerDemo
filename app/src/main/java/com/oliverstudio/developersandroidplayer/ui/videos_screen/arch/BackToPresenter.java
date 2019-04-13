package com.oliverstudio.developersandroidplayer.ui.videos_screen.arch;

import com.oliverstudio.developersandroidplayer.data.model.Video;

import java.util.List;

public interface BackToPresenter {

    void onSuccess(List<Video> videos, String nextPageToken);
    void onFailure();

}
