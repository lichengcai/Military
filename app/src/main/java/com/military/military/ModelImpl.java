package com.military.military;

import android.app.Activity;
import android.util.Log;

import com.military.MilitaryApplication;
import com.military.military.listener.OnLoadingListener;
import com.military.military.utils.FileUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by lichengcai on 2016/12/2.
 */

public class ModelImpl {
    private Document document = null;
    private Document documentDetail = null;

    //新闻详情，不需重新拉取
    protected Document getNewsDetailDocument(final Activity activity,
                                   final String url,
                                   final OnLoadingListener onLoadingListener) {

        final String fileName = FileUtils.hashKeyForDisk(url);

        MilitaryApplication.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String content = FileUtils.readFiles(activity,fileName);
                    if (content != null) {
                        documentDetail = new Document("");
                        documentDetail.html(content);
                        Log.d("ModelImpl"," file");
                    }else {
                        documentDetail = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").get();
                        FileUtils.writeFiles(activity,documentDetail.outerHtml(),fileName);
                        Log.d("ModelImpl"," internet");
                    }

                    if (documentDetail != null) {
                        onLoadingListener.onSuccess(documentDetail);
                    }else {
                        onLoadingListener.onFail();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return documentDetail;
    }

    protected Document getDocument(final Activity activity,
                                   final String url,
                                   final OnLoadingListener onLoadingListener) {

        final String fileName = FileUtils.hashKeyForDisk(url);
        final String fileTime = FileUtils.hashKeyForDisk(url + "time");

        MilitaryApplication.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String time = FileUtils.readFiles(activity,fileTime);
                    if (time != null) {
                        long t = Long.parseLong(time);
                        //超过五小时，重新拉取
                        if ((System.currentTimeMillis()-t)/1000/3600 > 5) {
                            document = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").get();
                            FileUtils.writeFiles(activity,document.outerHtml(),fileName);
                            FileUtils.writeFiles(activity,String.valueOf(System.currentTimeMillis()),fileTime);
                            Log.d("ModelImpl"," internet again");
                        }else {
                            String content = FileUtils.readFiles(activity,fileName);
                            document = new Document("");
                            document.html(content);
                            Log.d("ModelImpl"," file");
                        }
                    }else {
                        document = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").get();
                        FileUtils.writeFiles(activity,document.outerHtml(),fileName);
                        FileUtils.writeFiles(activity,String.valueOf(System.currentTimeMillis()),fileTime);
                        Log.d("ModelImpl"," internet");
                    }

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

        return document;
    }
}
