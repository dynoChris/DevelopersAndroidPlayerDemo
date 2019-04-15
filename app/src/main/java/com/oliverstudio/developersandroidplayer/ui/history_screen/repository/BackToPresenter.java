package com.oliverstudio.developersandroidplayer.ui.history_screen.repository;

import com.oliverstudio.developersandroidplayer.data.model.Video;

import java.util.List;

public interface BackToPresenter {
    void onSuccess(List<Video> videoList);
}
