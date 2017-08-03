package com.military.military.huanqiu.model;


import android.app.Activity;

import com.military.military.ModelImpl;
import com.military.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/11/28.
 */

public class NewsDetailModelImpl extends ModelImpl implements NewsDetailModel {
    private Activity activity;

    public NewsDetailModelImpl(Activity activity) {
        this.activity = activity;
    }
    @Override
    public void getDetailInfo(final String url, final OnLoadingListener onLoadingListener) {
        getNewsDetailDocument(activity,url,onLoadingListener);
    }
}
