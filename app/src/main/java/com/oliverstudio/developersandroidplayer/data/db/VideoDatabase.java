package com.oliverstudio.developersandroidplayer.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {VideoEntity.class}, version = 1)
public abstract class VideoDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
