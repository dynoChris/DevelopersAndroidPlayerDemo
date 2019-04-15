package com.oliverstudio.developersandroidplayer.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    public static final String API_KEY_YOUTUBE = "AIzaSyCs_leAFtYjYBJk4_1gDlJ-9dAyxgJYIWw";
    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";
    public static final String DEVELOPERS_ANDROID_PLAYLIST = "UUVHFbqXqoYvEWM1Ddxl0QDg";
    public static final int RESULTS_PER_PAGE = 25;
    public static final String INCLUDE_SNIPPET = "snippet";

    public static ApiYoutube getApiService() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setFieldNamingStrategy(new CustomFieldNamingPolicy())
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .serializeNulls()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ApiYoutube.class);
    }

    private static class CustomFieldNamingPolicy implements FieldNamingStrategy {
        @Override
        public String translateName(Field field) {
            String name = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field);
            name = name.substring(2, name.length()).toLowerCase();
            return name;
        }
    }

//    public static ApiYoutube getApiService() {
//
//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//
//        return retrofit.create(ApiYoutube.class);
//    }

}


