package com.military.huanqiu.persenter;

import android.app.Activity;
import android.content.Context;

import com.military.huanqiu.model.WeaponModel;
import com.military.huanqiu.model.WeaponModelImpl;
import com.military.huanqiu.view.WeaponView;
import com.military.listener.OnLoadingListener;

import org.jsoup.nodes.Document;

/**
 * Created by lichengcai on 2016/12/1.
 */

public class WeaponPresenter {
    private WeaponModel mModel;
    private WeaponView mView;

    public WeaponPresenter(Activity context, WeaponView view){
        this.mView = view;
        mModel = new WeaponModelImpl(context);
    }

    public void getWeaponListData(String url) {
        mModel.getWeaponData(url, new OnLoadingListener() {
            @Override
            public void onSuccess(Document json) {
                mView.setWeaponList(json.html());
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
