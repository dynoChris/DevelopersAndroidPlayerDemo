package com.oliverstudio.developersandroidplayer.domain.history_fragment;

import com.oliverstudio.developersandroidplayer.data.models.Video;

import java.util.List;

public interface BackToPresenter {
    void onSuccess(List<Video> videoList);
}
