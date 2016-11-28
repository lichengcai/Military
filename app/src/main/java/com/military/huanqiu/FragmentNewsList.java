package com.military.huanqiu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.military.R;
import com.military.bean.Channel;
import com.military.bean.NewsBean;
import com.military.huanqiu.persenter.NewsListPresenter;
import com.military.huanqiu.view.NewsListView;
import com.military.ui.fragment.FragmentBase;
import com.military.video.FragmentVideo;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/28.
 */

public class FragmentNewsList extends FragmentBase implements NewsListView{
    private String url = null;
    private NewsListPresenter mPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = (String) getArguments().getSerializable("url");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new NewsListPresenter(this);
        if (url != null) {
            mPresenter.setNewsList(url);
        }

    }

    public static FragmentNewsList newInstance(String url) {
        FragmentNewsList fragmentNewsList = new FragmentNewsList();
        Bundle bundle = new Bundle();
        bundle.putSerializable("url",url);
        fragmentNewsList.setArguments(bundle);
        return fragmentNewsList;
    }

    @Override
    public void setNewsList(ArrayList<NewsBean> arrayList) {

    }
}
