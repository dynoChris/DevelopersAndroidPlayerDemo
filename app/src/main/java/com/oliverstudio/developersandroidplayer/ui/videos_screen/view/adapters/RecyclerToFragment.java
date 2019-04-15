package com.oliverstudio.developersandroidplayer.ui.videos_screen.view.adapters;

import com.oliverstudio.developersandroidplayer.data.model.Video;

public interface RecyclerToFragment {

    void insertVideoToDB(Video video);
    void openVideo(int position);

}
