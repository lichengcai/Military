package com.military.military.video.model;

import com.military.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/11/23.
 */

public interface VideoModel {
    void getVideoData(String url, OnLoadingListener onLoadingListener);
}
