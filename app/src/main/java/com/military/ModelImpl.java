package com.military;

import android.app.Activity;
import android.util.Log;

import com.military.listener.OnLoadingListener;
import com.military.utils.FileUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by lichengcai on 2016/12/2.
 */

public class ModelImpl {
    private Document document = null;

    protected Document getDocument(final Activity activity,
                                   final String url,
                                   final String fileName,
                                   final String fileTime,
                                   final OnLoadingListener onLoadingListener) {
        MilitaryApplication.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String time = FileUtils.readFiles(activity,fileTime);
                    if (time != null) {
                        long t = Long.parseLong(time);
                        if ((System.currentTimeMillis()-t)/1000/3600 > 5) {
                            document = Jsoup.connect(url).get();
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
                        document = Jsoup.connect(url).get();
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
