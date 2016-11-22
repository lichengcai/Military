package com.military.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.military.R;
import com.military.bean.GuideBean;
import com.military.guide.adapter.GuideListAdapter;
import com.military.listener.OnItemClickListener;
import com.military.ui.activity.BaseActivity;
import com.military.soldier.MilitaryActivity;
import com.military.video.ChannelActivity;
import com.military.video.VideoActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/18.
 */

public class GuideActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private GuideListAdapter mAdapter;
    private ArrayList<GuideBean> mData= new ArrayList<>();
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_COUNT = CPU_COUNT+1;
    private static final int MAXNUM_POOL_COUNT = CPU_COUNT*2+1;
    private static final long KEEP_TIME = 10L;


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
                    Toast.makeText(GuideActivity.this," position--" + position,Toast.LENGTH_SHORT).show();
                    switch (position) {
                        case 0:
                            startActivity(new Intent(GuideActivity.this, MilitaryActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(GuideActivity.this, VideoActivity.class));
                            break;
                    }
                }
            });
        }

    }

    private void init() {
        mData.add(new GuideBean("Military","http://himg2.huanqiu.com/attachment2010/2016/1118/08/34/20161118083436763.jpg"));
        mData.add(new GuideBean("Video","http://mvavatar1.meitudata.com/54dc1847cc6bc2367.jpg!thumb60"));
        mAdapter = new GuideListAdapter(this,mData);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setAdapter(mAdapter);
    }


}
