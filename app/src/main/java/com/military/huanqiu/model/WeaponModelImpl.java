package com.military.huanqiu.model;

import android.content.Context;

import com.military.MilitaryApplication;
import com.military.listener.OnLoadingListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lichengcai on 2016/12/1.
 */

public class WeaponModelImpl implements WeaponModel {
    private Context context;

    public WeaponModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getWeaponData(final String url, final OnLoadingListener onLoadingListener) {
        MilitaryApplication.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(url).get();
                    if (document != null) {
                        onLoadingListener.onSuccess(document);
                    }else {
                        onLoadingListener.onFail();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
