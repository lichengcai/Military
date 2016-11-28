package com.military.huanqiu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.military.R;
import com.military.bean.Channel;
import com.military.bean.NewsBean;
import com.military.huanqiu.adapter.FocusListAdapter;
import com.military.huanqiu.persenter.MilitaryPresenter;
import com.military.huanqiu.view.MilitaryView;
import com.military.ui.fragment.FragmentBase;
import com.military.video.FragmentVideo;
import com.military.widget.convenientbanner.ConvenientBanner;
import com.military.widget.convenientbanner.holder.CBViewHolderCreator;
import com.military.widget.convenientbanner.holderview.NetworkImageHolderView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/24.
 */

public class FragmentFocus extends FragmentBase implements MilitaryView{
    ConvenientBanner mBanner;
    @BindView(R.id.recyclerFocus)
    RecyclerView mRecycler;

    private static final int MSG_GET_BANNER_SUCCESS =0;
    private static final int MSG_GET_LIST_SUCCESS = 1;
    private String url = "http://mil.huanqiu.com/";
    private MilitaryPresenter mPresenter;
    private FocusListAdapter mListAdapter;

    private FocusHandler mHandler = new FocusHandler(this);
    private static class FocusHandler extends Handler {
        private WeakReference<FragmentFocus> ref;
        private FragmentFocus frg;

        FocusHandler(FragmentFocus fragmentFocus) {
            ref = new WeakReference<>(fragmentFocus);
            frg= ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_BANNER_SUCCESS:
                    ArrayList<NewsBean> arrayList = (ArrayList<NewsBean>) msg.obj;
                    Log.d("array"," array_banner--" + arrayList.size());
                    for (int i=0; i<arrayList.size(); i++) {
                        Log.d("array"," array_banner--" + arrayList.get(i).toString());
                    }
                    frg.mBanner.setPages(new CBViewHolderCreator() {
                        @Override
                        public Object createHolder() {
                            return new NetworkImageHolderView();
                        }
                    },arrayList);
                    frg.mBanner.startTurning(5000);//设置轮播开始自动循环
                    frg.mBanner.setScrollDuration(2000);//设置滑动速度
                    break;
                case MSG_GET_LIST_SUCCESS:
                    frg.mRecycler.setLayoutManager(new LinearLayoutManager(frg.getActivity()));
                    frg.mRecycler.setAdapter(frg.mListAdapter);
                    break;
            }
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_focus,container,false);
        ButterKnife.bind(this,view);
        mBanner = (ConvenientBanner) LayoutInflater.from(getActivity()).inflate(R.layout.header_focus,null).findViewById(R.id.bannerFocus);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new MilitaryPresenter(this);
        mPresenter.getFocusData(url);
    }


    @Override
    public void setBannerFocus(ArrayList<NewsBean> arrayList) {
        if (mHandler != null)
            mHandler.obtainMessage(MSG_GET_BANNER_SUCCESS,arrayList).sendToTarget();
    }

    @Override
    public void setListFocus(ArrayList<NewsBean> arrayList) {
        if (mHandler != null)
            mListAdapter = new FocusListAdapter(getActivity(),arrayList);
            mHandler.obtainMessage(MSG_GET_LIST_SUCCESS,arrayList).sendToTarget();
    }
}
