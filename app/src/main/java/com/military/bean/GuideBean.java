package com.military.bean;

/**
 * Created by lichengcai on 2016/11/18.
 */

public class GuideBean {
    private String name;
    private String imgUrl;

    public GuideBean(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
