package com.military.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
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
    private ArrayList<Channel> mArrayUnChecked = new ArrayList<>();
    private ArrayList<Channel> mArrayAll = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        ButterKnife.bind(this);

        addAllChannel();
        Log.d("ArrayList"," mSelected--" + mSelected.size());
        Log.d("ArrayList"," mALL--" + mArrayAll.size());

        mAdapter = new ChannelAdapter(mContext,mSelected);
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
                Animation ani = AnimationUtils.loadAnimation(ChannelActivity.this,R.anim.channelani);
                final Channel channel = mAdapter.getItem(position);
                Log.d("channel","channel--" + channel.toString());

                view.startAnimation(ani);
                ani.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        mArrayAll.add(channel);
                        mAllAdapter.addItem();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mSelected.remove(position);
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
            public void onItemClick(View view, final int position) {
                Animation ani = AnimationUtils.loadAnimation(ChannelActivity.this,R.anim.channelani);
                final Channel channel = mAdapter.getItem(position);
                Log.d("channel","channel--" + channel.toString());

                view.startAnimation(ani);
                ani.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        mSelected.add(channel);
                        mAdapter.addItem();

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mArrayAll.remove(position);
                        mAllAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

    }

    private void addAllChannel() {
        Channel ch_joke = new Channel("joke",13);
        Channel ch_star = new Channel("star",16);
        Channel ch_beauty = new Channel("beauty",19);
        Channel ch_dance = new Channel("dance",63);
        Channel ch_music = new Channel("music",62);
        Channel ch_food = new Channel("food",59);
        Channel ch_meizhuang = new Channel("meizhuang",27);
        Channel ch_nanshen = new Channel("nanshen",31);
        Channel ch_baby = new Channel("baby",18);
        Channel ch_habit = new Channel("habit",6);
        Channel ch_chixiu = new Channel("chixiu",423);
        Channel ch_shougong = new Channel("shougong",450);

        mArrayAll.add(ch_joke);
        mArrayAll.add(ch_star);
        mArrayAll.add(ch_beauty);
        mArrayAll.add(ch_dance);
        mArrayAll.add(ch_music);
        mArrayAll.add(ch_food);
        mArrayAll.add(ch_meizhuang);
        mArrayAll.add(ch_nanshen);
        mArrayAll.add(ch_baby);
        mArrayAll.add(ch_habit);
        mArrayAll.add(ch_chixiu);
        mArrayAll.add(ch_shougong);

        for (int i=0; i<mArrayAll.size(); i++) {
            for (int j=0; j<mSelected.size(); j++) {
                if (!mArrayAll.get(i).equals(mSelected.get(j))) {
                    mArrayUnChecked.add(mSelected.get(j));
                }
            }
        }
    }


}
