package com.oliverstudio.developersandroidplayer.di.modules;

import com.google.gson.GsonBuilder;
import com.oliverstudio.developersandroidplayer.network.ApiYoutube;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public ApiYoutube provideApiYoutube() {
        return new Retrofit.Builder()
                .baseUrl(ApiYoutube.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build().create(ApiYoutube.class);
    }

}