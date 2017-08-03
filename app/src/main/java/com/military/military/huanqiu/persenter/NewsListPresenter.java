package com.military.military.huanqiu.persenter;

import android.app.Activity;
import android.util.Log;

import com.military.military.bean.NewsBean;
import com.military.military.huanqiu.model.NewsListModel;
import com.military.military.huanqiu.model.NewsListModelImpl;
import com.military.military.huanqiu.view.NewsListView;
import com.military.military.listener.OnLoadingListener;

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
    private int mCurrentPage = 1;

    public NewsListPresenter(Activity activity,NewsListView mView){
        this.mView = mView;
        mModel = new NewsListModelImpl(activity);
    }

    public void setNewsList(String url, final boolean loadMore) {
        String linkUrl ;
        if (loadMore) {
            mCurrentPage ++;

            linkUrl = url + mCurrentPage + ".html" ;
        }else {
            linkUrl = url;
        }
        mModel.getListData(linkUrl, new OnLoadingListener() {
            @Override
            public void onSuccess(Document json) {
                ArrayList<NewsBean> arrayList = getNewsList(json);
                if (arrayList.size() != 0) {
                    mView.setNewsList(arrayList,loadMore);
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

    private ArrayList<NewsBean> getNewsList(Document document) {
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
}
