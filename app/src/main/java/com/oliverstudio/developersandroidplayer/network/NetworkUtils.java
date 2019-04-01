package com.oliverstudio.developersandroidplayer.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    public static final String API_KEY = "AIzaSyCs_leAFtYjYBJk4_1gDlJ-9dAyxgJYIWw";
    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    public static final String DEVELOPERS_ANDROID_PLAYLIST = "UUVHFbqXqoYvEWM1Ddxl0QDg";
    public static final int RESULTS_PER_PAGE = 25;
    public static final String INCLUDE_SNIPPET = "snippet";

    public static ApiService getApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

}
