package com.military.huanqiu;

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
import com.military.bean.NewsBean;
import com.military.bean.WeaponBean;
import com.military.huanqiu.adapter.NewsListAdapter;
import com.military.huanqiu.persenter.NewsListPresenter;
import com.military.huanqiu.view.NewsListView;
import com.military.listener.OnItemClickListener;
import com.military.ui.fragment.FragmentBase;

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
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private NewsListAdapter mAdapter;
    private String url = null;
    private NewsListPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private NewsListHandler mHandler = new NewsListHandler(this);

    private static final int MSG_GET_DATA_SUCCESS = 0;
    private static final int MSG_GET_DATA_MORE = 1;
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
                    if (frg.mSwipeRefresh != null)
                        frg.mSwipeRefresh.setRefreshing(false);


                    frg.mRecyclerView.setLayoutManager(frg.mLayoutManager);
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

                case MSG_GET_DATA_MORE:
                    ArrayList<NewsBean> array = (ArrayList<NewsBean>) msg.obj;
                    frg.mAdapter.loadMore(array);
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
        mLayoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new NewsListPresenter(getActivity(),this);
        if (url != null) {
            mPresenter.setNewsList(url,false);
        }
        if (isAdded()) {
            mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.divider));
        }

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.setNewsList(url,false);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && mAdapter.isShowFooter()) {
                    mPresenter.setNewsList(url,true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    public static FragmentNewsList newInstance(String url) {
        FragmentNewsList fragmentNewsList = new FragmentNewsList();
        Bundle bundle = new Bundle();
        bundle.putSerializable("url",url);
        fragmentNewsList.setArguments(bundle);
        return fragmentNewsList;
    }

    @Override
    public void setNewsList(ArrayList<NewsBean> arrayList,boolean loadMore) {

        if (mHandler != null) {
            if (loadMore) {
                mHandler.obtainMessage(MSG_GET_DATA_MORE,arrayList).sendToTarget();
            }else {
                mAdapter = new NewsListAdapter(getActivity(),arrayList);
                mHandler.sendEmptyMessage(MSG_GET_DATA_SUCCESS);
            }
            if (arrayList.size() == 0) {
                mAdapter.setIsShowFooter(false);
            }else {
                mAdapter.setIsShowFooter(true);
            }
        }
    }
}
