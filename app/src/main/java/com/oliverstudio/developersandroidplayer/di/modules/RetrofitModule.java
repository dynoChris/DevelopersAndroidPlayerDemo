package com.oliverstudio.developersandroidplayer.di.modules;

import com.google.gson.GsonBuilder;
import com.oliverstudio.developersandroidplayer.data.network.ApiYoutube;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public ApiYoutube provideApiYoutube() {
        return new Retrofit.Builder()
                .baseUrl(ApiYoutube.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build().create(ApiYoutube.class);
    }

}