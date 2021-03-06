package com.military.military.huanqiu.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.military.R;
import com.military.military.video.adapter.VideoPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MilitaryActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private VideoPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_military);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);

        mAdapter = new VideoPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new FragmentFocus(),"关注");
        mAdapter.addFragment(FragmentNewsList.newInstance("http://mil.huanqiu.com/china/"),"国内");
        //http://mil.huanqiu.com/china/index.html
        //http://mil.huanqiu.com/china/2.html
        mAdapter.addFragment(FragmentNewsList.newInstance("http://mil.huanqiu.com/world/"),"国外");
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
