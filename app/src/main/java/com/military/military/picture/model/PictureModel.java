package com.military.military.picture.model;

import com.military.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/12/8.
 */

public interface PictureModel {
    void getPicData(String url, OnLoadingListener onLoadingListener);
    void getPicDataDetail(String url, OnLoadingListener onLoadingListener);
}
