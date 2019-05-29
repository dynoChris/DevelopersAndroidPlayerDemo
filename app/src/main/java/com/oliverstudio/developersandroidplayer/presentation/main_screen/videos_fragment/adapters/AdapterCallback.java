package com.oliverstudio.developersandroidplayer.presentation.main_screen.videos_fragment.adapters;

import com.oliverstudio.developersandroidplayer.data.models.Video;

public interface AdapterCallback {

    void insertVideoToDB(Video video);
    void openVideo(int position);

}
