package com.military.military.huanqiu.persenter;

import android.app.Activity;
import android.util.Log;

import com.military.military.bean.WeaponBean;
import com.military.military.huanqiu.model.WeaponModel;
import com.military.military.huanqiu.model.WeaponModelImpl;
import com.military.military.huanqiu.view.WeaponView;
import com.military.military.listener.OnLoadingListener;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;


/**
 * Created by lichengcai on 2016/12/1.
 */

public class WeaponPresenter {
    private WeaponModel mModel;
    private WeaponView mView;
    private int mCurrentPage = 1;

    public WeaponPresenter(Activity context, WeaponView view){
        this.mView = view;
        mModel = new WeaponModelImpl(context);
    }

    public void getWeaponListData(String url, final boolean loadMore) {
        String linkUrl ;
        if (loadMore) {
            mCurrentPage ++;
            int units = mCurrentPage%10;
            int tens = (mCurrentPage/10)%10;
            int hundred = (mCurrentPage/100)%10;

            linkUrl = url + "_" + hundred + "_" + tens + "_" + units;
        }else {
            linkUrl = url;
        }
        Log.d("getWeaponListData","linkUrl--" + linkUrl);
        mModel.getWeaponData(linkUrl, new OnLoadingListener() {
            @Override
            public void onSuccess(Document json) {
                ArrayList<WeaponBean> arrayList = new ArrayList<>();
                Elements elements = json.select("div.picList");

                Elements weapon = elements.get(0).select("span.pic");
                Elements country = elements.get(0).select("div.country");
                Elements category = elements.get(0).select("span.category");

                if (weapon.size() == country.size() && country.size() == category.size()) {
                    for (int i=0; i<weapon.size(); i++) {
                        WeaponBean weaponBean = new WeaponBean();
                        weaponBean.setName(weapon.get(i).select("img").attr("alt"));
                        weaponBean.setImgUrl(weapon.get(i).select("img").attr("src"));
                        weaponBean.setLinkUrl("http://weapon.huanqiu.com" + weapon.get(i).select("a").attr("href"));
                        weaponBean.setDescribe(weapon.get(i).text());
                        weaponBean.setCountryName(country.get(i).select("img").attr("alt"));
                        weaponBean.setContryImg(country.get(i).select("img").attr("src"));
                        weaponBean.setCategory(category.get(i).text());
                        arrayList.add(weaponBean);
                    }

                }
                mView.setWeaponList(arrayList,loadMore);

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
