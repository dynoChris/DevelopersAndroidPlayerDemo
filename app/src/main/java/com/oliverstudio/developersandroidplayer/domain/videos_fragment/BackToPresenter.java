package com.oliverstudio.developersandroidplayer.domain.videos_fragment;

import com.oliverstudio.developersandroidplayer.data.models.Video;

import java.util.List;

public interface BackToPresenter {

    void onSuccess(List<Video> videos, String nextPageToken);
    void onFailure();

}
