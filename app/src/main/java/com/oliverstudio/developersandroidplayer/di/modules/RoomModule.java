package com.oliverstudio.developersandroidplayer.di.modules;

import android.arch.persistence.room.Room;

import com.oliverstudio.developersandroidplayer.App;
import com.oliverstudio.developersandroidplayer.data.db.VideoDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Provides
    @Singleton
    public VideoDatabase provideVideoDatabase() {
        return Room.databaseBuilder(App.getAppComponent().getContext(), VideoDatabase.class, VideoDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

}
