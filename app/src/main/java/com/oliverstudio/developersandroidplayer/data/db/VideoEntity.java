package com.oliverstudio.developersandroidplayer.data.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class VideoEntity {

    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo(name = "id_video")
    private String idVideo;
    @ColumnInfo(name = "url_image")
    private String urlImage;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "time_post")
    private String timePost;

    public VideoEntity(String idVideo, String urlImage, String title, String timePost) {
        this.idVideo = idVideo;
        this.urlImage = urlImage;
        this.title = title;
        this.timePost = timePost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimePost() {
        return timePost;
    }

    public void setTimePost(String timePost) {
        this.timePost = timePost;
    }
}

