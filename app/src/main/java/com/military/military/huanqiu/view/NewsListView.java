package com.military.military.huanqiu.view;

import com.military.military.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/11/28.
 */

public interface NewsListView {
    void setNewsList(ArrayList<NewsBean> arrayList,boolean loadMore);
}
