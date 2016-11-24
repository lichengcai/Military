package com.military.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.military.MilitaryApplication;
import com.military.R;
import com.military.bean.Channel;
import com.military.video.adapter.VideoPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/22.
 */

public class VideoActivity extends AppCompatActivity {
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

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ButterKnife.bind(this);
        getIntent();

        init();
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(VideoActivity.this,ChannelActivity.class),0);
            }
        });
    }

    private void init() {
        if (MilitaryApplication.mSelected.size() == 0) {
            Channel ch_joke = new Channel("搞笑",13);
            Channel ch_star = new Channel("明星",16);
            Channel ch_beauty = new Channel("女神",19);
            MilitaryApplication.mSelected.add(ch_beauty);
            MilitaryApplication.mSelected.add(ch_star);
            MilitaryApplication.mSelected.add(ch_joke);
        }
        mAdapter = new VideoPagerAdapter(getSupportFragmentManager());
        for (int i=0; i<MilitaryApplication.mSelected.size(); i++) {
            mAdapter.addFragment(FragmentVideo.newInstance(MilitaryApplication.mSelected.get(i)),MilitaryApplication.mSelected.get(i).getName());
        }

        mViewPager.setOffscreenPageLimit(MilitaryApplication.mSelected.size());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            if (requestCode == 0) {
                mAdapter.clearData();
                for (int i=0; i<MilitaryApplication.mSelected.size(); i++) {
                    mAdapter.addFragment(FragmentVideo.newInstance(MilitaryApplication.mSelected.get(i)),MilitaryApplication.mSelected.get(i).getName());
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
