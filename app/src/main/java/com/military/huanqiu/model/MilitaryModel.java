package com.military.huanqiu.model;

import com.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/11/24.
 */

public interface MilitaryModel {
    void getMilitaryData(String url, OnLoadingListener onLoadingListener);
}
