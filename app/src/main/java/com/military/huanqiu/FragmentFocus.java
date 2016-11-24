package com.military.huanqiu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.military.R;
import com.military.bean.Channel;
import com.military.huanqiu.persenter.MilitaryPresenter;
import com.military.huanqiu.view.MilitaryView;
import com.military.ui.fragment.FragmentBase;
import com.military.video.FragmentVideo;
import com.military.widget.convenientbanner.ConvenientBanner;

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

    private String url = "http://mil.huanqiu.com/";
    private MilitaryPresenter mPresenter;

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
    public void setBannerFocus() {

    }

    @Override
    public void setListFocus() {

    }
}
