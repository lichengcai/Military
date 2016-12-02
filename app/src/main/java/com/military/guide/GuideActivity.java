package com.military.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.military.R;
import com.military.bean.GuideBean;
import com.military.guide.adapter.GuideListAdapter;
import com.military.huanqiu.MilitaryActivity;
import com.military.huanqiu.weapon.WeaponActivity;
import com.military.listener.OnItemClickListener;
import com.military.ui.activity.BaseActivity;
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
                            startActivity(new Intent(GuideActivity.this, VideoActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(GuideActivity.this, WeaponActivity.class));
                            break;
                    }
                }
            });
        }

    }

    private void init() {
        mData.add(new GuideBean("Military","http://himg2.huanqiu.com/attachment2010/2016/1118/08/34/20161118083436763.jpg"));
        mData.add(new GuideBean("Video","http://www.meipai.com/favicon.ico?1"));
        mData.add(new GuideBean("Weapon","http://images.huanqiu.com/sarons/2014/03/991a6d96550e9c189e19b62471d9d01f.jpg"));
        mAdapter = new GuideListAdapter(this,mData);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(mAdapter);
    }


    public void change(View view) {

    }

}
