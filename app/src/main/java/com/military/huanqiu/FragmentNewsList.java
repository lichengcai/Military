package com.military.huanqiu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.military.ui.fragment.FragmentBase;
import com.military.video.FragmentVideo;
import com.military.video.adapter.VideoAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/28.
 */

public class FragmentNewsList extends FragmentBase implements NewsListView{
    @BindView(R.id.recyclerVideo)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    LinearLayout mLayoutLoading;

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
                    if (frg.mLayoutLoading != null)
                        frg.mLayoutLoading.setVisibility(View.GONE);

                    frg.mRecyclerView.setLayoutManager(new LinearLayoutManager(frg.getContext()));
                    frg.mRecyclerView.setAdapter(frg.mAdapter);
                    break;
            }
        }
    }

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
