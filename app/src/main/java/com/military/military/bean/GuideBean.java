package com.military.military.bean;

/**
 * Created by lichengcai on 2016/11/18.
 */

public class GuideBean {
    private String name;
    private String imgUrl;
    private int id;

    public GuideBean(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public GuideBean(String name,int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
