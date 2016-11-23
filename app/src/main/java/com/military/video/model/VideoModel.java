package com.military.video.model;

import com.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/11/23.
 */

public interface VideoModel {
    void getVideoData(String url, OnLoadingListener onLoadingListener);
}
