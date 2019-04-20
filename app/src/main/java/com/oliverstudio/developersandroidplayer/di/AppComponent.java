package com.oliverstudio.developersandroidplayer.di;

import android.content.Context;

import com.oliverstudio.developersandroidplayer.di.modules.ContextModule;
import com.oliverstudio.developersandroidplayer.di.modules.RetrofitModule;
import com.oliverstudio.developersandroidplayer.di.modules.RoomModule;
import com.oliverstudio.developersandroidplayer.domain.history_fragment.HistoryRepository;
import com.oliverstudio.developersandroidplayer.domain.videos_fragment.VideosRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RoomModule.class, RetrofitModule.class, ContextModule.class})
public interface AppComponent {

    Context getContext();

    void inject(VideosRepository repository);
    void inject(HistoryRepository repository);
}
