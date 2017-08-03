package com.military.military.picture.model;

import android.app.Activity;

import com.military.military.ModelImpl;
import com.military.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/12/8.
 */

public class PictureModelImpl extends ModelImpl implements PictureModel {
    private Activity activity;

    public PictureModelImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void getPicData(String url, OnLoadingListener onLoadingListener) {
        getDocument(activity,url,onLoadingListener);
    }

    @Override
    public void getPicDataDetail(String url, OnLoadingListener onLoadingListener) {
        getNewsDetailDocument(activity,url,onLoadingListener);
    }
}
