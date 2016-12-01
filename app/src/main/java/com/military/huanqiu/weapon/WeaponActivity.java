package com.military.huanqiu.weapon;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.military.R;
import com.military.bean.CategoryBean;
import com.military.huanqiu.adapter.CategoryAdapter;
import com.military.huanqiu.persenter.WeaponPresenter;
import com.military.huanqiu.view.WeaponView;
import com.military.ui.activity.BaseActivity;
import com.military.utils.FileUtils;

import java.io.File;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lichengcai on 2016/11/29.
 */

public class WeaponActivity extends BaseActivity implements WeaponView{
    @BindView(R.id.list_category)
    ExpandableListView mListCategory;
    @BindView(R.id.content)
    TextView mContent;

    private static final String url = "http://weapon.huanqiu.com/weaponlist/";
    private String fileName  = FileUtils.hashKeyForDisk(url);
    private String fileTime = FileUtils.hashKeyForDisk(url + "time");
    private WeaponPresenter mPresenter;
    private WeaponHandler mHandler = new WeaponHandler(this);
    private static final int MSG_GET_WEAPON_DATA = 0;


    private static class WeaponHandler extends Handler {
        private WeakReference<WeaponActivity> ref;
        private WeaponActivity act;

        WeaponHandler(WeaponActivity activity) {
            ref = new WeakReference<>(activity);
            act = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_WEAPON_DATA:

                    final String content = (String) msg.obj;
                    act.mContent.setText(content);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            FileUtils.writeFiles(act,content,act.fileName);
                            FileUtils.writeFiles(act,String.valueOf(System.currentTimeMillis()),act.fileTime);
                        }
                    }).start();
                    break;
            }
        }
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon);
        ButterKnife.bind(this);

//        mPresenter = new WeaponPresenter(this,this);
//        setWeaponCategoryList();
//
//
//        String time = FileUtils.readFiles(this,fileTime);
//        if (time != null) {
//            if ((System.currentTimeMillis() - Long.parseLong(time))/1000 > 100) {
//                this.deleteFile(fileName);
//                this.deleteFile(fileTime);
//
//                mPresenter.getWeaponListData(url);
//                Log.d("WeaponActivity"," internet again");
//
//            }else {
//                String content = FileUtils.readFiles(this,fileName);
//                if (content != null) {
//                    mContent.setText(content);
//                    Log.d("WeaponActivity"," file");
//                }
//            }
//        }else {
//            mPresenter.getWeaponListData(url);
//            Log.d("WeaponActivity"," internet");
//        }
//
//        for (String name : this.fileList()) {
//            Log.d("WeaponActivity"," name---" + name);
//        }
    }


    private void setWeaponCategoryList() {
        CategoryAdapter mAdapter;
        mListCategory.setGroupIndicator(null);
        mAdapter = new CategoryAdapter(mContext,CategoryBean.getCategoryData());
        mListCategory.setAdapter(mAdapter);
    }

    @Override
    public void setWeaponList(String content) {
        if (mHandler != null)
            mHandler.obtainMessage(MSG_GET_WEAPON_DATA,content).sendToTarget();
    }
}
