package com.military.bean;

import java.io.Serializable;

/**
 * Created by lichengcai on 2016/11/24.
 */

public class NewsBean implements Serializable{
    private String title;
    private String imgUrl;
    private String linkUrl;
    private String time;
    private String detailTitle;


    @Override
    public String toString() {
        return "NewsBean{" +
                "title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", time='" + time + '\'' +
                ", detailTitle='" + detailTitle + '\'' +
                '}';
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
