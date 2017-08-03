package com.military.military.bean;

import java.io.Serializable;

/**
 * Created by lichengcai on 2016/11/23.
 */

public class Video implements Serializable{
    private String title;
    private String imgUrl;
    private String videoUrl;

    @Override
    public String toString() {
        return "Video{" +
                "title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
