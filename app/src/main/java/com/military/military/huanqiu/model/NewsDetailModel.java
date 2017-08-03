package com.military.military.huanqiu.model;

import com.military.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/11/28.
 */

public interface NewsDetailModel {
    void getDetailInfo(String url, OnLoadingListener onLoadingListener);
}
