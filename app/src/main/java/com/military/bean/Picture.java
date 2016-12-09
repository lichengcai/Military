package com.military.bean;

/**
 * Created by lichengcai on 2016/12/9.
 */

public class Picture {
    private String title;
    private String img_url;


    @Override
    public String toString() {
        return "Picture{" +
                "title='" + title + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
