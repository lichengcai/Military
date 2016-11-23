package com.military.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.military.R;
import com.military.ui.activity.BaseActivity;
import com.military.video.adapter.VideoPagerAdapter;

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
    private VideoPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        initView();

        mAdapter = new VideoPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(FragmentVideo.newInstance(1),getString(R.string.all_channel));
        mAdapter.addFragment(FragmentVideo.newInstance(2),getString(R.string.all_channel));
        mAdapter.addFragment(FragmentVideo.newInstance(3),getString(R.string.all_channel));

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.all_channel));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.all_channel));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.all_channel));

    }
}
