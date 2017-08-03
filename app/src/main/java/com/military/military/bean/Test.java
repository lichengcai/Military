package com.military.military.bean;

/**
 * Created by lichengcai on 2016/11/18.
 */

public class Test {
    private String title;
    private String imgUrl;
    private int id;

    public Test(String title,String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
        id = 0;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
