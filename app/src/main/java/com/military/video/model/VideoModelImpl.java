package com.military.video.model;

import android.app.Activity;

import com.military.MilitaryApplication;
import com.military.ModelImpl;
import com.military.listener.OnLoadingListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

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
