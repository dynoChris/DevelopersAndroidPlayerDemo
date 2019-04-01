package com.oliverstudio.developersandroidplayer.data.model;

public class Video {

    private String urlImage;
    private String title;
    private String timePost;

    public Video(String urlImage, String title, String timePost) {
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
}
