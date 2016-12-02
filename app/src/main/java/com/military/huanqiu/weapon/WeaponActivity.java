package com.military.huanqiu.weapon;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.military.R;
import com.military.bean.CategoryBean;
import com.military.bean.WeaponBean;
import com.military.huanqiu.adapter.CategoryAdapter;
import com.military.huanqiu.adapter.WeaponListAdapter;
import com.military.huanqiu.persenter.WeaponPresenter;
import com.military.huanqiu.view.WeaponView;
import com.military.listener.OnItemClickListener;
import com.military.ui.activity.BaseActivity;
import com.military.utils.FileUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lichengcai on 2016/11/29.
 */

public class WeaponActivity extends AppCompatActivity implements WeaponView{
//    @BindView(R.id.list_category)
//    ExpandableListView mListCategory;
    @BindView(R.id.recyclerWeaponList)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    LinearLayout mLayoutLoading;
    @BindView(R.id.text_category)
    TextView mTextCategory;

    private static final String url = "http://weapon.huanqiu.com/weaponlist/aircraft/list_1_0";
    private WeaponPresenter mPresenter;
    private WeaponListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
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
                    act.mLayoutManager = new GridLayoutManager(act,2);
                    act.mRecyclerView.setLayoutManager(act.mLayoutManager);
                    act.mRecyclerView.setAdapter(act.mAdapter);
                    if (act.mLayoutLoading != null){
                        act.mLayoutLoading.setVisibility(View.GONE);
                    }

                    act.mAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                        }
                    });
                    break;
            }
        }
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);

        mPresenter = new WeaponPresenter(this,this);
//        setWeaponCategoryList();
        CategoryAdapter mAdapter;
        mAdapter = new CategoryAdapter(WeaponActivity.this,CategoryBean.getCategoryData());
        CategoryBean categoryBean = mAdapter.getCategoryBean(1,0);

        mPresenter.getWeaponListData(categoryBean.getLinkUrl());
        mTextCategory.setText(categoryBean.getName());

    }


//    private void setWeaponCategoryList() {
//        CategoryAdapter mAdapter;
//        mListCategory.setGroupIndicator(null);
//        mAdapter = new CategoryAdapter(mContext,CategoryBean.getCategoryData());
//        mListCategory.setAdapter(mAdapter);
//    }

    @Override
    public void setWeaponList(ArrayList<WeaponBean> arrayList) {
        for (int i=0; i<arrayList.size(); i++) {
            Log.d("setWeaponList"," array to string--" + arrayList.get(i).toString());
        }
        mAdapter = new WeaponListAdapter(WeaponActivity.this,arrayList);
        if (mHandler != null)
            mHandler.sendEmptyMessage(MSG_GET_WEAPON_DATA);
    }
}
