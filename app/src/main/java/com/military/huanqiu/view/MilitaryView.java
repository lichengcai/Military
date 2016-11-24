package com.military.huanqiu.view;

import com.military.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/11/24.
 */

public interface MilitaryView {
    void setBannerFocus(ArrayList<NewsBean> arrayList);
    void setListFocus(ArrayList<NewsBean> arrayList);
}
