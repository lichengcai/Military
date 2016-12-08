package com.military.picture.model;

import com.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/12/8.
 */

public interface PictureModel {
    void getPicData(String url, OnLoadingListener onLoadingListener);
}
