package com.military.guide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.military.R;
import com.military.bean.GuideBean;
import com.military.guide.adapter.GuideListAdapter;
import com.military.huanqiu.news.MilitaryActivity;
import com.military.huanqiu.weapon.WeaponActivity;
import com.military.listener.OnItemClickListener;
import com.military.picture.PictureActivity;
import com.military.ui.activity.BaseActivity;
import com.military.ui.activity.TestActivity;
import com.military.utils.SDCardHelper;
import com.military.video.VideoActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/18.
 */

public class GuideActivity extends BaseActivity  {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private GuideListAdapter mAdapter;
    private ArrayList<GuideBean> mData= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        init();
        setAllListener();
        SDCardHelper.saveFileToSDCardPrivateFilesDir(new byte[]{1,1,1,},"","SDFile",getApplicationContext());
        Log.d("SDCard","all size--"+ SDCardHelper.getSDCardSize() + "   free size---" + SDCardHelper.getSDCardFreeSize()
        + "   value size--- " + SDCardHelper.getSDCardAvailableSize()
        + "  isSDCardMounted---" + SDCardHelper.isSDCardMounted()
        + "  getSDCardBaseDir---" + SDCardHelper.getSDCardBaseDir());
    }

    private void setAllListener() {
        if (mAdapter != null) {
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(GuideActivity.this, MilitaryActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(GuideActivity.this, WeaponActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(GuideActivity.this, VideoActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(GuideActivity.this, PictureActivity.class));
                            break;
                        case 4:
                            startActivity(new Intent(GuideActivity.this, TestActivity.class));
                            break;
                    }
                }
            });
        }

    }

    private void init() {
        mData.add(new GuideBean("军事环球",R.drawable.military));
        mData.add(new GuideBean("武器库",R.drawable.weapon));
        mData.add(new GuideBean("视频",R.drawable.video));
        mData.add(new GuideBean("图片",R.drawable.picture));
        mData.add(new GuideBean("Android",R.drawable.img_android));


        mAdapter = new GuideListAdapter(this,mData);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setAdapter(mAdapter);
    }

}
