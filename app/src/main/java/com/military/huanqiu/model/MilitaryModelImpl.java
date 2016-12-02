package com.military.huanqiu.model;

import android.app.Activity;

import com.military.MilitaryApplication;
import com.military.ModelImpl;
import com.military.listener.OnLoadingListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by lichengcai on 2016/11/24.
 */

public class MilitaryModelImpl extends ModelImpl implements MilitaryModel {
    private Activity activity;

    public MilitaryModelImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void getMilitaryData(final String url, final OnLoadingListener onLoadingListener) {
        getDocument(activity,url,onLoadingListener);

    }
}
