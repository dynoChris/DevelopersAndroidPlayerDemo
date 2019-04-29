package com.oliverstudio.developersandroidplayer.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {VideoEntity.class}, version = 1)
public abstract class VideoDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "videos_db";

    public abstract DaoAccess daoAccess();
}
