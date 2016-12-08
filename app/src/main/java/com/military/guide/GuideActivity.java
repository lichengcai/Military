package com.military.guide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.military.R;
import com.military.bean.GuideBean;
import com.military.bean.WeaponBean;
import com.military.guide.adapter.GuideListAdapter;
import com.military.huanqiu.MilitaryActivity;
import com.military.huanqiu.weapon.WeaponActivity;
import com.military.huanqiu.weapon.WeaponDetailActivity;
import com.military.listener.OnItemClickListener;
import com.military.ui.activity.BaseActivity;
import com.military.ui.activity.TestActivity;
import com.military.video.VideoActivity;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;

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
        mData.add(new GuideBean("视频",R.drawable.video));

        mAdapter = new GuideListAdapter(this,mData);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(mAdapter);
    }

}
