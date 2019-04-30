
package com.oliverstudio.developersandroidplayer.data.network.response.list_videos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResourceId {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("videoId")
    @Expose
    private String videoId;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

}
