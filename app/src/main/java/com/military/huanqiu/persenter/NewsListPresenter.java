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

    public void setNewsList(String url) {
        mModel.getListData(url, new OnLoadingListener() {
            @Override
            public void onSuccess(Document json) {
                Elements elements = json.select("div.fallsFlow");
                Log.d("setNewsList","elements--" + elements.size());
                for (Element element : elements) {
                    Elements hrefs =  element.select("h3");
                    for (Element element1 : hrefs) {
                        Log.d("setNewsList"," href--" + element1.attr("a[href]"));
                    }
                }
//                for (Element element : elements) {
//                    Elements links = element.select("a");
//                    for (Element element1 : links) {
//                        NewsBean newsBean = new NewsBean();
//                        newsBean.setTitle(element1.attr("title"));
//                        newsBean.setLinkUrl(element1.attr("href"));
//                        Log.d("setNewsList","title--" + element1.attr("title"));
//                        Log.d("setNewsList","href--" + element1.attr("href"));
//                    }
//                }
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
