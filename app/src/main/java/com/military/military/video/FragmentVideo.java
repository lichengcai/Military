package com.military.military.video;

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
import com.military.military.bean.Channel;
import com.military.military.bean.Video;
import com.military.military.ui.fragment.FragmentBase;
import com.military.military.video.adapter.VideoAdapter;
import com.military.military.video.presenter.VideoPresenter;
import com.military.military.video.view.VideoView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/22.
 */

public class FragmentVideo extends FragmentBase implements VideoView {
    private static String URL = "http://www.meipai.com/square/";
    @BindView(R.id.recyclerVideo)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    LinearLayout mLayoutLoading;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private VideoAdapter mAdapter;
    private Channel mChannel;
    private VideoPresenter mPresenter;
    private static final int MSG_GET_DATA_SUCCESS = 0;
    private static final int MSG_GET_DATA_EMPTY = 1;
    private static final int MSG_GET_DATA_FAIL = 2;


    private VideoHandler mHandler = new VideoHandler(this);

    private static class VideoHandler extends Handler {
        private WeakReference<FragmentVideo> ref;
        private FragmentVideo frg;

        VideoHandler(FragmentVideo fragmentVideo) {
            ref = new WeakReference<>(fragmentVideo);
            frg = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_DATA_SUCCESS:
                    if (frg.mSwipeRefresh != null)
                        frg.mSwipeRefresh.setRefreshing(false);
                    if (frg.mLayoutLoading != null)
                        frg.mLayoutLoading.setVisibility(View.GONE);
                    ArrayList<Video> array = (ArrayList<Video>) msg.obj;
                    if (array != null) {
                        frg.mAdapter = new VideoAdapter(frg.getActivity(),array);
                    }
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new VideoPresenter(getActivity(),this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPresenter.getVideoInfo(URL+mChannel.getId());

        if (isAdded())
            mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.divider));
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getVideoInfo(URL+mChannel.getId());
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChannel = (Channel) getArguments().getSerializable("channel");
    }


    public static FragmentVideo newInstance(Channel channel) {
        FragmentVideo fragmentListNews = new FragmentVideo();
        Bundle bundle = new Bundle();
        bundle.putSerializable("channel",channel);
        fragmentListNews.setArguments(bundle);
        return fragmentListNews;
    }

    @Override
    public void setVideoData(ArrayList<Video> arrayList) {
        if (arrayList.size() == 0) {
            mHandler.sendEmptyMessage(MSG_GET_DATA_EMPTY);
        }else {
            mHandler.obtainMessage(MSG_GET_DATA_SUCCESS,arrayList).sendToTarget();
        }
    }
}
