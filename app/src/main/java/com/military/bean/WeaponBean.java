package com.military.bean;

import java.io.Serializable;

/**
 * Created by lichengcai on 2016/12/2.
 */

public class WeaponBean implements Serializable{
    private String name;
    private String imgUrl;
    private String countryName;
    private String countryImg;
    private String category;
    private String describe;
    private String linkUrl;

    @Override
    public String toString() {
        return "WeaponBean{" +
                "name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryImg='" + countryImg + '\'' +
                ", category='" + category + '\'' +
                ", describe='" + describe + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                '}';
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContryImg() {
        return countryImg;
    }

    public void setContryImg(String countryImg) {
        this.countryImg = countryImg;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
