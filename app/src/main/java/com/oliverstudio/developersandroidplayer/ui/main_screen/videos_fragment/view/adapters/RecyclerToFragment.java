package com.oliverstudio.developersandroidplayer.ui.main_screen.videos_fragment.view.adapters;

import com.oliverstudio.developersandroidplayer.data.model.Video;

public interface RecyclerToFragment {

    void insertVideoToDB(Video video);
    void openVideo(int position);

}
