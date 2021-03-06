package com.military.military.huanqiu.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.military.R;
import com.military.military.bean.NewsBean;
import com.military.military.huanqiu.adapter.FocusAdapter;
import com.military.military.huanqiu.persenter.MilitaryPresenter;
import com.military.military.huanqiu.view.MilitaryView;
import com.military.military.listener.OnItemClickListener;
import com.military.military.ui.fragment.FragmentBase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/24.
 */

public class FragmentFocus extends FragmentBase implements MilitaryView {
    @BindView(R.id.layout_loading)
    LinearLayout mLayout_loading;
    @BindView(R.id.recyclerFocus)
    RecyclerView mRecycler;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private static final int MSG_GET_LIST_SUCCESS = 1;
    private String url = "http://mil.huanqiu.com/";
    private MilitaryPresenter mPresenter;
    private FocusAdapter mListAdapter;

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
                case MSG_GET_LIST_SUCCESS:
                    if (frg.mSwipeRefresh != null)
                        frg.mSwipeRefresh.setRefreshing(false);
                    if (frg.mLayout_loading != null)
                        frg.mLayout_loading.setVisibility(View.GONE);
                    frg.mRecycler.setLayoutManager(new LinearLayoutManager(frg.getActivity()));
                    frg.mRecycler.setAdapter(frg.mListAdapter);
                    frg.mListAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(frg.getActivity(),NewsDetailActivity.class);
                            intent.putExtra("newsBean",frg.mListAdapter.getListItem(position));
                            frg.startActivity(intent);
                        }
                    });
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
        mPresenter = new MilitaryPresenter(getActivity(),this);
        mPresenter.getFocusData(url);
        if (isAdded()) {
            mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.divider));
        }

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getFocusData(url);
            }
        });

    }


    @Override
    public void setFocus(ArrayList<NewsBean> array_banner,ArrayList<NewsBean> array_list) {
        if (mHandler != null)
            mListAdapter = new FocusAdapter(getActivity(),array_banner,array_list);
        if (mHandler != null) {
            mHandler.sendEmptyMessage(MSG_GET_LIST_SUCCESS);
        }
    }
}
