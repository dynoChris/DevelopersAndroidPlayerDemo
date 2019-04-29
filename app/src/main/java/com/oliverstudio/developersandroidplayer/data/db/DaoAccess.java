package com.oliverstudio.developersandroidplayer.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    void insertVideo(VideoEntity video);

    @Query("SELECT * FROM " + VideoEntity.TABLE_NAME)
    List<VideoEntity> fetchAllVideos();

//    @Query("SELECT * FROM Movie WHERE id =:id")
//    Movie fetchOneMovieByMovieId(int id);

}
