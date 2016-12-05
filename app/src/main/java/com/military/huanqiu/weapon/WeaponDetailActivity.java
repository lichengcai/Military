package com.military.huanqiu.weapon;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.military.MilitaryApplication;
import com.military.R;
import com.military.bean.WeaponBean;
import com.military.huanqiu.FragmentFocus;
import com.military.huanqiu.FragmentNewsList;
import com.military.huanqiu.adapter.CommonTabPagerAdapter;
import com.military.ui.activity.BaseActivity;
import com.military.video.adapter.VideoAdapter;
import com.military.video.adapter.VideoPagerAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeaponDetailActivity extends BaseActivity implements CommonTabPagerAdapter.TabPagerListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.ad_view)
    ImageView mAdView;

    private CommonTabPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_detail);
        ButterKnife.bind(this);

        WeaponBean weaponBean = (WeaponBean) getIntent().getSerializableExtra("weaponBean");
        if (weaponBean != null) {
            setTitle(weaponBean.getName());
            collapsingToolbar.setTitle(weaponBean.getName());
            collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的
            adapter = new CommonTabPagerAdapter(getSupportFragmentManager()
                    , 3, Arrays.asList("详情", "参数", "图集"), this);
            adapter.setListener(this);
            viewpager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewpager);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        }else {
            Toast.makeText(this,"获取详情失败",Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    @Override
    public Fragment getFragment(int position) {
        if (position == 0) {
            return FragmentNewsList.newInstance("http://mil.huanqiu.com/world/");
        }else {
            return FragmentNewsList.newInstance("http://mil.huanqiu.com/china/");
        }
    }
}
