package com.military.soldier;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.bm.library.PhotoView;
import com.military.R;
import com.military.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MilitaryActivity extends BaseActivity {
//    @BindView(R.id.viewPager)
//    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_military);
        ButterKnife.bind(this);

        PhotoView photoView = (PhotoView) findViewById(R.id.img);
        photoView.enable();


    }
}
