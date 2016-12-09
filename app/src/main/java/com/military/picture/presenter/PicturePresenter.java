package com.military.picture.presenter;

import android.app.Activity;
import android.util.Log;

import com.military.bean.Video;
import com.military.listener.OnLoadingListener;
import com.military.picture.model.PictureModel;
import com.military.picture.model.PictureModelImpl;
import com.military.picture.view.PictureView;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/12/8.
 */

public class PicturePresenter {
    private PictureModel mModel;
    private PictureView mView;
    private int mCurrentPage = 1;

    public PicturePresenter(PictureView mView, Activity activity) {
        this.mView = mView;
        mModel = new PictureModelImpl(activity);
    }

    public void getPicture(String url, final boolean loadMore){
        String linkUrl ;
        if (loadMore) {
            mCurrentPage ++;
            linkUrl = url + mCurrentPage + ".html";
        }else {
            linkUrl = url;
        }
        mModel.getPicData(linkUrl, new OnLoadingListener() {
            @Override
            public void onSuccess(Document json) {
                ArrayList<Video> arrayList = getPicBean(json);
                mView.setPicture(arrayList,loadMore);
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

    private ArrayList<Video> getPicBean(Document document) {
        ArrayList<Video> arrayList = new ArrayList<>();
        Elements elements = document.select("div.fallsFlow").select("li.item");
        for (Element element : elements) {
            Log.d("getPicBean", "img---" + element.select("img").get(0).attr("src"));
            Log.d("getPicBean", "text---" + element.select("a").get(0).attr("title"));
            Log.d("getPicBean", "url---" + element.select("a").get(0).attr("href"));
            Video video = new Video();
            video.setTitle(element.select("a").get(0).attr("title"));
            video.setVideoUrl(element.select("a").get(0).attr("href"));
            video.setImgUrl(element.select("img").get(0).attr("src"));
            arrayList.add(video);
        }

        return arrayList;
    }
}
