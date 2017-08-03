package com.military.military.huanqiu.persenter;

import android.app.Activity;
import android.util.Log;

import com.military.military.huanqiu.model.NewsDetailModel;
import com.military.military.huanqiu.model.NewsDetailModelImpl;
import com.military.military.huanqiu.view.NewsDetailView;
import com.military.military.listener.OnLoadingListener;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/11/28.
 */

public class NewsDetailPresenter {
    private NewsDetailModel mModel;
    private NewsDetailView mView;

    public NewsDetailPresenter(Activity activity,NewsDetailView newsDetailView) {
        this.mView = newsDetailView;
        mModel = new NewsDetailModelImpl(activity);
    }

    private String getContent(Document document) {
        Elements contents = document.select("div#text p:gt(0)");//表示 div 中包含 0 个以上的 p
        String content = contents.text();
        Log.d("getDetailInfo"," content---" + content);
        return content;
    }

    private ArrayList<String> getImgs(Document document) {
        ArrayList<String> imgs = new ArrayList<>();
        Elements elements = document.select("div#atlas img");
        for (Element element : elements) {
            imgs.add(element.attr("src"));
        }
        return imgs;
    }
    public void getDetailInfo(String url) {
        mModel.getDetailInfo(url, new OnLoadingListener() {
            @Override
            public void onSuccess(Document json) {
                String content = getContent(json);
                ArrayList<String> imgs = getImgs(json);
                mView.setDetailContent(content);
                mView.setImgs(imgs);

            }

            @Override
            public void onFail() {

            }

            @Override
            public void onEmpty() {

            }

            @Override
            public void onLoading() {

            }
        });
    }
}
