package com.oliverstudio.developersandroidplayer;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.oliverstudio.developersandroidplayer.data.db.VideoDatabase;

public class App extends Application {

    private static App instance;
    private VideoDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, VideoDatabase.class, VideoDatabase.DATABASE_NAME)
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public VideoDatabase getDatabase() {
        return database;
    }

}
