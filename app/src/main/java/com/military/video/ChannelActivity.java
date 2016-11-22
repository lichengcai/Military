package com.military.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.military.R;
import com.military.SpaceItemDecoration;
import com.military.bean.Channel;
import com.military.listener.OnItemClickListener;
import com.military.ui.activity.BaseActivity;
import com.military.video.adapter.ChannelAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/22.
 */

public class ChannelActivity extends BaseActivity {
    @BindView(R.id.recyclerMy)
    RecyclerView mRecyclerMyChannel;
    @BindView(R.id.recyclerAll)
    RecyclerView mRecyclerAllChannel;

    private ChannelAdapter mAdapter;
    private ChannelAdapter mAllAdapter;
    private ArrayList<Channel> mData = new ArrayList<>();
    private ArrayList<Channel> mArrayAll = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        ButterKnife.bind(this);

        initData();
        mAdapter = new ChannelAdapter(mContext,mData);
        mAllAdapter = new ChannelAdapter(mContext,mArrayAll);

        mRecyclerMyChannel.setLayoutManager(new GridLayoutManager(mContext,4));
        mRecyclerMyChannel.addItemDecoration(new SpaceItemDecoration(0,0,dip2px(5),dip2px(5)));
        mRecyclerMyChannel.setAdapter(mAdapter);

        mRecyclerAllChannel.setLayoutManager(new GridLayoutManager(mContext,4));
        mRecyclerAllChannel.addItemDecoration(new SpaceItemDecoration(0,0,dip2px(5),dip2px(5)));
        mRecyclerAllChannel.setAdapter(mAllAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                Animation ani = new AlphaAnimation(1,0);
                ani.setDuration(500);
                view.startAnimation(ani);
                ani.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mData.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        mAllAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"position--" + position,Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void initData() {
        mData.clear();
        for (int i=0; i<20; i++) {
            Channel channel = new Channel("channel" + i,i);
            mData.add(channel);
        }

        mArrayAll.clear();
        for (int i=0; i<20; i++){
            Channel ch = new Channel("All " + i,i);
            mArrayAll.add(ch);
        }
    }
}
