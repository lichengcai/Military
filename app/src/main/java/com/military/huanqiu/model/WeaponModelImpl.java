package com.military.huanqiu.model;

import android.app.Activity;
import com.military.ModelImpl;
import com.military.listener.OnLoadingListener;


/**
 * Created by lichengcai on 2016/12/1.
 */

public class WeaponModelImpl extends ModelImpl implements WeaponModel {
    private Activity activity;

    public WeaponModelImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void getWeaponData(final String url, final OnLoadingListener onLoadingListener) {
        getDocument(activity,url,onLoadingListener);
    }


}
