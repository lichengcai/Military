package com.military.huanqiu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.military.R;
import com.military.bean.NewsBean;
import com.military.bean.Video;
import com.military.huanqiu.adapter.NewsListAdapter;
import com.military.huanqiu.persenter.NewsListPresenter;
import com.military.huanqiu.view.NewsListView;
import com.military.listener.OnItemClickListener;
import com.military.ui.fragment.FragmentBase;
import com.military.video.FragmentVideo;
import com.military.video.adapter.VideoAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by lichengcai on 2016/11/28.
 */

public class FragmentNewsList extends FragmentBase implements NewsListView{
    @BindView(R.id.recyclerVideo)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    LinearLayout mLayoutLoading;
    @BindView(R.id.frame)
    PtrClassicFrameLayout frame;

    private NewsListAdapter mAdapter;
    private String url = null;
    private NewsListPresenter mPresenter;
    private NewsListHandler mHandler = new NewsListHandler(this);

    private static final int MSG_GET_DATA_SUCCESS = 0;
    private static class NewsListHandler extends Handler {
        private WeakReference<FragmentNewsList> ref;
        private FragmentNewsList frg;

        NewsListHandler(FragmentNewsList fragment) {
            ref = new WeakReference<>(fragment);
            frg = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_DATA_SUCCESS:
                    if (frg.frame != null) {
                        frg.frame.refreshComplete();
                    }
                    if (frg.mLayoutLoading != null)
                        frg.mLayoutLoading.setVisibility(View.GONE);

                    frg.mRecyclerView.setLayoutManager(new LinearLayoutManager(frg.getContext()));
                    frg.mRecyclerView.setAdapter(frg.mAdapter);
                    frg.mAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(frg.getActivity(),NewsDetailActivity.class);
                            intent.putExtra("newsBean",frg.mAdapter.getItem(position));
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
        View view = inflater.inflate(R.layout.fragment_video,container,false);
        ButterKnife.bind(this,view);

        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.setPadding(0, 60, 0, 60);
        header.initWithString("English");
//        header.setBackgroundColor(getResources().getColor(R.color.black));
        header.setTextColor(getResources().getColor(R.color.black));

        frame.setDurationToCloseHeader(1500);
        frame.setHeaderView(header);
        frame.addPtrUIHandler(header);

        frame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (TextUtils.isEmpty(url)) {
                    mPresenter.setNewsList(url);
                }

            }
        });
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
        for (int i=0; i<arrayList.size(); i++) {
            Log.d("NewsList","array size---" + arrayList.get(i).toString());
        }
        if (arrayList.size() != 0) {

            mAdapter = new NewsListAdapter(getActivity(),arrayList);
            if (mHandler != null) {
                mHandler.sendEmptyMessage(MSG_GET_DATA_SUCCESS);
            }
        }
    }
}
