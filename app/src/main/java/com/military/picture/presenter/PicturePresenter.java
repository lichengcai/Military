package com.military.picture.presenter;

import android.app.Activity;
import android.util.Log;

import com.military.listener.OnLoadingListener;
import com.military.picture.model.PictureModel;
import com.military.picture.model.PictureModelImpl;
import com.military.picture.view.PictureView;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by lichengcai on 2016/12/8.
 */

public class PicturePresenter {
    private PictureModel mModel;
    private PictureView mView;

    public PicturePresenter(PictureView mView, Activity activity) {
        this.mView = mView;
        mModel = new PictureModelImpl(activity);
    }

    public void getPicture(String url,boolean loadMore){
        if (loadMore) {

        }
        mModel.getPicData(url, new OnLoadingListener() {
            @Override
            public void onSuccess(Document json) {
                getPicBean(json);
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

    private void getPicBean(Document document) {
        Elements elements = document.select("div.listPicBox");
        for (Element element : elements) {
            Log.d("getPicBean","elements" + element.html());
        }

    }
}
