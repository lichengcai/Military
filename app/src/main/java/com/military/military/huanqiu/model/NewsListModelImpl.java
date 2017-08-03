package com.military.military.huanqiu.model;

import android.app.Activity;

import com.military.military.ModelImpl;
import com.military.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/11/28.
 */

public class NewsListModelImpl extends ModelImpl implements NewsListModel {
    private Activity activity;

    public NewsListModelImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void getListData(final String url, final OnLoadingListener onLoadingListener) {
        getDocument(activity,url,onLoadingListener);

    }
}
