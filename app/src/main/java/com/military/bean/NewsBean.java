package com.military.bean;

/**
 * Created by lichengcai on 2016/11/24.
 */

public class NewsBean {
    private String title;
    private String imgUrl;
    private String linkUrl;

    @Override
    public String toString() {
        return "NewsBean{" +
                "title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
