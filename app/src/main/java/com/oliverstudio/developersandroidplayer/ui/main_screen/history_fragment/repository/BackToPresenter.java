package com.oliverstudio.developersandroidplayer.ui.main_screen.history_fragment.repository;

import com.oliverstudio.developersandroidplayer.data.model.Video;

import java.util.List;

public interface BackToPresenter {
    void onSuccess(List<Video> videoList);
}
