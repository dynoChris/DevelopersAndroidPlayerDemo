package com.oliverstudio.developersandroidplayer.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    void insertVideo(VideoEntity video);

    @Query("SELECT * FROM VideoEntity")
    List<VideoEntity> fetchAllVideos();

//    @Query("SELECT * FROM Movie WHERE id =:id")
//    Movie fetchOneMovieByMovieId(int id);

}
