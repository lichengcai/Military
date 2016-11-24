package com.military.huanqiu.persenter;

import android.util.Log;

import com.military.bean.NewsBean;
import com.military.huanqiu.model.MilitaryModel;
import com.military.huanqiu.model.MilitaryModelImpl;
import com.military.huanqiu.view.MilitaryView;
import com.military.listener.OnLoadingListener;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/11/24.
 */

public class MilitaryPresenter {
    private MilitaryModel mModel;
    private MilitaryView mView;

    public MilitaryPresenter(MilitaryView view) {
        this.mView = view;
        mModel = new MilitaryModelImpl();
    }

    private ArrayList<NewsBean> getBannerInfo(Document document) {
        ArrayList<NewsBean> arrayList = new ArrayList<>();
        Elements images = document.select("[width=300]");
        Elements titles = document.select("div.imgTitle");
        if (images.size() == titles.size()) {
            for (int i=0; i<images.size(); i++) {
                NewsBean newsBean = new NewsBean();
                newsBean.setTitle(titles.text());
                newsBean.setImgUrl(images.attr("src"));
                arrayList.add(newsBean);
            }
        }
        return arrayList;
    }

    private ArrayList<NewsBean> getListInfo(Document document) {
        ArrayList<NewsBean> arrayList = new ArrayList<>();
        Elements links = document.select("div.newsFir");
        Log.d("links"," links---" + links.size());
        for (Element element : links) {
            Elements elements = element.select("a");
            for (Element element1 : elements) {
                NewsBean newsBean = new NewsBean();
                newsBean.setTitle(element1.attr("title"));
                newsBean.setLinkUrl(element1.attr("href"));
                Log.d("getListInfo","title--" + element1.attr("title"));
                Log.d("getListInfo","href--" + element1.attr("href"));
                arrayList.add(newsBean);
            }
        }
        return arrayList;
    }
    public void getFocusData(String url) {
        mModel.getMilitaryData(url, new OnLoadingListener() {
            @Override
            public void onSuccess(Document json) {
                ArrayList<NewsBean> array_banner = getBannerInfo(json);
                if (array_banner != null && array_banner.size() != 0) {
                    mView.setBannerFocus(array_banner);
                }
                ArrayList<NewsBean> array_list = getListInfo(json);
                if (array_list != null && array_list.size() !=0) {
                    mView.setListFocus(array_list);
                }
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
