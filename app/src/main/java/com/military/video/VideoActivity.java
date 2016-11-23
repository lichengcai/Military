package com.military.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.military.R;
import com.military.bean.Channel;
import com.military.ui.activity.BaseActivity;
import com.military.video.adapter.VideoPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/22.
 */

public class VideoActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private VideoPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        init();
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(VideoActivity.this,ChannelActivity.class),0);
            }
        });
    }

    private void init() {
        if (mSelected.size() == 0) {
            Channel ch_joke = new Channel("joke",13);
            Channel ch_star = new Channel("star",16);
            Channel ch_beauty = new Channel("beauty",19);
            mSelected.add(ch_beauty);
            mSelected.add(ch_star);
            mSelected.add(ch_joke);
        }
        mAdapter = new VideoPagerAdapter(getSupportFragmentManager());
        for (int i=0; i<mSelected.size(); i++) {
            mAdapter.addFragment(FragmentVideo.newInstance(mSelected.get(i)),mSelected.get(i).getName());
        }

        mViewPager.setOffscreenPageLimit(mSelected.size());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            if (requestCode == 0) {
                Log.d("onActivityResult","requestCode==0");
            }
        }
    }
}
