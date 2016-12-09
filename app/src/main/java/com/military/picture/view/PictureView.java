package com.military.picture.view;

import com.military.bean.Picture;
import com.military.bean.Video;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/12/8.
 */

public interface PictureView {
    void setPicture(ArrayList<Video> arrayList,boolean loadMore);
    void setPicDetail(ArrayList<Picture> arrayList);
}
