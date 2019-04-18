package com.oliverstudio.developersandroidplayer.network;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    public static ApiYoutube getApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiYoutube.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        return retrofit.create(ApiYoutube.class);
    }

//    public static ApiYoutube getApiService() {
//
//        Gson gson = new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
//                .setFieldNamingStrategy(new CustomFieldNamingPolicy())
//                .setPrettyPrinting()
//                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//                .serializeNulls()
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//
//        return retrofit.create(ApiYoutube.class);
//    }
//
//    private static class CustomFieldNamingPolicy implements FieldNamingStrategy {
//        @Override
//        public String translateName(Field field) {
//            String name = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field);
//            name = name.substring(2, name.length()).toLowerCase();
//            return name;
//        }
//    }



}


