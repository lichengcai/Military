package com.military.military.huanqiu.model;

import com.military.military.listener.OnLoadingListener;

/**
 * Created by lichengcai on 2016/12/1.
 */

public interface WeaponModel {
    void getWeaponData(String url, OnLoadingListener onLoadingListener);
}
