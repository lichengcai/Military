package com.military.huanqiu.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.military.MilitaryApplication;
import com.military.ModelImpl;
import com.military.listener.OnLoadingListener;
import com.military.utils.FileUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by lichengcai on 2016/12/1.
 */

public class WeaponModelImpl extends ModelImpl implements WeaponModel {
    private Document document = null;
    private Activity activity;

    public WeaponModelImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void getWeaponData(final String url, final OnLoadingListener onLoadingListener) {
        final String fileName = FileUtils.hashKeyForDisk(url);
        final String fileTime = FileUtils.hashKeyForDisk(url + "time");

        getDocument(activity,url,fileName,fileTime,onLoadingListener);
    }


}
