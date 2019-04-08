package com.oliverstudio.developersandroidplayer.presentation.youtube_player_screen;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.oliverstudio.developersandroidplayer.R;
import com.oliverstudio.developersandroidplayer.network.NetworkUtils;


public class YouTubePlayerActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        final YouTubePlayerView player = findViewById(R.id.youtube_player);
        player.initialize(NetworkUtils.API_KEY_YOUTUBE, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                if(!wasRestored) {
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });


    }
}
