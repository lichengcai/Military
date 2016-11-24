package com.military.huanqiu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.military.R;
import com.military.bean.Channel;
import com.military.bean.NewsBean;
import com.military.huanqiu.persenter.MilitaryPresenter;
import com.military.huanqiu.view.MilitaryView;
import com.military.ui.fragment.FragmentBase;
import com.military.video.FragmentVideo;
import com.military.widget.convenientbanner.ConvenientBanner;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/24.
 */

public class FragmentFocus extends FragmentBase implements MilitaryView{
    @BindView(R.id.bannerFocus)
    ConvenientBanner mBanner;
    @BindView(R.id.recyclerFocus)
    RecyclerView mRecycler;

    private static final int MSG_GET_BANNER_SUCCESS =0;
    private static final int MSG_GET_LIST_SUCCESS = 1;
    private String url = "http://mil.huanqiu.com/";
    private MilitaryPresenter mPresenter;

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

                    break;
                case MSG_GET_LIST_SUCCESS:

                    break;
            }
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_focus,container,false);
        ButterKnife.bind(this,view);
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
            mHandler.obtainMessage(MSG_GET_LIST_SUCCESS,arrayList).sendToTarget();
    }
}
