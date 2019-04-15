package com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.repository;

import com.oliverstudio.developersandroidplayer.data.model.Video;

import java.util.List;

public interface BackToPresenter {

    void onSuccess(List<Video> videos, String nextPageToken);
    void onFailure();

}
