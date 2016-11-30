package com.military.bean;

/**
 * Created by chengcai on 2016/11/29.
 */

public class CategoryBean {
    private String name;
    private String linkUrl;
    private String imgUrl;


    @Override
    public String toString() {
        return "CategoryBean{" +
                "name='" + name + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

