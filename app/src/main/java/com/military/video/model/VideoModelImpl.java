package com.military.video.model;

import com.military.MilitaryApplication;
import com.military.listener.OnLoadingListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by lichengcai on 2016/11/23.
 */

public class VideoModelImpl implements VideoModel {


    @Override
    public void getVideoData(final String url, final OnLoadingListener onLoadingListener) {
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
