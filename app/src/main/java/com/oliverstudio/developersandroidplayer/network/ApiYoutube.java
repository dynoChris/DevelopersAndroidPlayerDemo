package com.oliverstudio.developersandroidplayer.network;

import com.oliverstudio.developersandroidplayer.network.response.list_videos.ListVideosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiYoutube {

    String API_KEY_YOUTUBE = "AIzaSyCs_leAFtYjYBJk4_1gDlJ-9dAyxgJYIWw";
    String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    String DEVELOPERS_ANDROID_PLAYLIST = "UUVHFbqXqoYvEWM1Ddxl0QDg";
    int RESULTS_PER_PAGE = 25;
    String INCLUDE_SNIPPET = "snippet";

    //https://www.googleapis.com/youtube/v3/playlistItems
    // ?playlistId=UUVHFbqXqoYvEWM1Ddxl0QDg
    // &maxResults=25
    // &part=snippet
    // &key=AIzaSyCs_leAFtYjYBJk4_1gDlJ-9dAyxgJYIWw
    @GET("playlistItems")
    Call<ListVideosResponse> getVideos(
            @Query("playlistId") String playlistId,
            @Query("pageToken") String nextPageToken,
            @Query("maxResults") int resultsPerPage,
            @Query("part") String includeContent,
            @Query("key") String apiKey
    );

}
