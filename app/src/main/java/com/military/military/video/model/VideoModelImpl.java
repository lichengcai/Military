package com.military.military.video.model;

import android.app.Activity;

import com.military.military.ModelImpl;
import com.military.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/11/23.
 */

public class VideoModelImpl extends ModelImpl implements VideoModel {
    private Activity activity;

    public VideoModelImpl(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void getVideoData(final String url, final OnLoadingListener onLoadingListener) {
        getDocument(activity,url,onLoadingListener);

    }
}
