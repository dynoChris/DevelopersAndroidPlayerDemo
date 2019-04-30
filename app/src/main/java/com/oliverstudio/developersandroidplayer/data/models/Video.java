package com.oliverstudio.developersandroidplayer.data.models;

public class Video {

    private String idVideo;
    private String urlImage;
    private String title;
    private String timePost;

    public Video(String idVideo, String urlImage, String title, String timePost) {
        this.idVideo = idVideo;
        this.urlImage = urlImage;
        this.title = title;
        this.timePost = timePost;
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

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }
}
