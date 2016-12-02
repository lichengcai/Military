package com.military.huanqiu.model;


import android.app.Activity;

import com.military.MilitaryApplication;
import com.military.ModelImpl;
import com.military.listener.OnLoadingListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

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
