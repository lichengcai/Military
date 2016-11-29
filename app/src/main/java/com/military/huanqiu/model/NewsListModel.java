package com.military.huanqiu.model;

import com.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/11/28.
 */

public interface NewsListModel {
    void getListData(String url, OnLoadingListener onLoadingListener);
}
