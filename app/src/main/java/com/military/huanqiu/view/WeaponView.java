package com.military.huanqiu.view;

import com.military.bean.WeaponBean;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/12/1.
 */

public interface WeaponView {
    void setWeaponList(ArrayList<WeaponBean> arrayList,boolean loadMore);
}
