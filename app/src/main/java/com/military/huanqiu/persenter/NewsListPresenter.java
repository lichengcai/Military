package com.military.huanqiu.persenter;

import android.util.Log;

import com.military.bean.NewsBean;
import com.military.huanqiu.model.NewsListModel;
import com.military.huanqiu.model.NewsListModelImpl;
import com.military.huanqiu.view.NewsListView;
import com.military.listener.OnLoadingListener;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/11/28.
 */

public class NewsListPresenter {
    private NewsListModel mModel;
    private NewsListView mView;

    public NewsListPresenter(NewsListView mView){
        this.mView = mView;
        mModel = new NewsListModelImpl();
    }

    public ArrayList<NewsBean> getNewsList(Document document) {
        ArrayList<NewsBean> arrayList = new ArrayList<>();
        Elements elements = document.select("div.fallsFlow");
        for (Element element : elements) {

            Elements elements1 =  element.select("h3");
            Elements elements2 =  element.select("h5");
            Elements elements3 =  element.select("h6");
            if (elements1.size() == elements2.size() && elements2.size() == elements3.size()) {
                for (int i=0; i<elements1.size(); i++) {
                    NewsBean newsBean = new NewsBean();
                    newsBean.setLinkUrl(elements1.get(i).select("a").attr("href"));
                    newsBean.setTitle(elements1.get(i).text());
                    newsBean.setDetailTitle(elements2.get(i).text());
                    newsBean.setTime(elements3.get(i).text());
                    arrayList.add(newsBean);
                }

            }
        }

        for (int i=0; i<arrayList.size(); i++) {
            Log.d("setNewsList","array--" + arrayList.get(i).toString());
        }
        return arrayList;
    }
    public void setNewsList(String url) {
        mModel.getListData(url, new OnLoadingListener() {
            @Override
            public void onSuccess(Document json) {
                ArrayList<NewsBean> arrayList = getNewsList(json);
                if (arrayList.size() != 0) {
                    mView.setNewsList(arrayList);
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
