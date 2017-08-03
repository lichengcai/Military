package com.military.military.huanqiu.model;

import android.app.Activity;

import com.military.military.ModelImpl;
import com.military.military.listener.OnLoadingListener;

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
