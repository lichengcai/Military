package com.military.military.picture;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.military.R;
import com.military.military.bean.CategoryBean;
import com.military.military.bean.Picture;
import com.military.military.bean.Video;
import com.military.military.listener.OnItemClickListener;
import com.military.military.picture.adapter.PictureAdapter;
import com.military.military.picture.adapter.PictureSelectAdapter;
import com.military.military.picture.presenter.PicturePresenter;
import com.military.military.picture.view.PictureView;
import com.military.military.ui.activity.BaseActivity;
import com.military.military.widget.customdialog.BottomDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureActivity extends BaseActivity implements PictureView {
    @BindView(R.id.recyclerPic)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    LinearLayout mLayoutLoading;
    @BindView(R.id.text_category)
    TextView mCategory;
    @BindView(R.id.img_select)
    ImageView mImgSelect;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private PicturePresenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private ListView mListView ;
    private PictureAdapter mAdapter;
    PictureSelectAdapter mSelectAdapter;
    CategoryBean category ;
    private static final int MSG_GET_PICTURE_DATA_SUCCESS = 0;
    private static final int MSG_GET_PICTURE_DATA_MORE = 1;
    private PictureHandler mHandler = new PictureHandler(this);

    private static class PictureHandler extends Handler {
        private WeakReference<PictureActivity> ref;
        private PictureActivity act;

        PictureHandler(PictureActivity activity) {
            ref = new WeakReference<>(activity);
            act = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_PICTURE_DATA_SUCCESS:
                    if (act.mLayoutLoading != null)
                        act.mLayoutLoading.setVisibility(View.GONE);
                    if (act.mSwipeRefresh.isRefreshing()){
                        act.mSwipeRefresh.setRefreshing(false);
                    }
                    act.mRecyclerView.setAdapter(act.mAdapter);

                    act.mAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(act.mContext,PictureDetailActivity.class);
                            Video video = act.mAdapter.getItemBean(position);
                            intent.putExtra("Video", video);
                            act.startActivity(intent);
                        }
                    });
                    break;
                case MSG_GET_PICTURE_DATA_MORE:
                    ArrayList<Video> array = (ArrayList<Video>) msg.obj;
                    act.mAdapter.loadMore(array);
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        mSelectAdapter = new PictureSelectAdapter(PictureActivity.this);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.divider));
        category = mSelectAdapter.getCategory(0);
        mPresenter = new PicturePresenter(this,this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (category != null) {
            mCategory.setText(category.getName());

            mPresenter.getPicture(category.getLinkUrl(),false);

            setAllListener();
        }else {
            finish();
        }

    }

    private void setAllListener() {
        mImgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select();
            }
        });

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getPicture(category.getLinkUrl(),false);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && mAdapter.isShowFooter()) {
                    mPresenter.getPicture(category.getLinkUrl(),true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void setPicture(ArrayList<Video> arrayList,boolean loadMore) {

        if (mHandler != null) {
            if (loadMore) {
                mHandler.obtainMessage(MSG_GET_PICTURE_DATA_MORE,arrayList).sendToTarget();
            }else {
                mAdapter = new PictureAdapter(mContext,arrayList);
                mHandler.sendEmptyMessage(MSG_GET_PICTURE_DATA_SUCCESS);
            }
            if (arrayList.size() == 0) {
                mAdapter.setIsShowFooter(false);
            }else {
                mAdapter.setIsShowFooter(true);
            }
        }
    }

    @Override
    public void setPicDetail(ArrayList<Picture> arrayList) {

    }

    private void select(){
        final BottomDialog mDialog = BottomDialog.create(getSupportFragmentManager());

        mDialog.setViewListener(new BottomDialog.ViewListener() {
            @Override
            public void bindView(View v) {
                mListView = (ListView) v.findViewById(R.id.list_pic_select);
                mListView.setAdapter(mSelectAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        category = mSelectAdapter.getCategory(position);
                        if (category != null) {
                            if (mLayoutLoading != null)
                                mLayoutLoading.setVisibility(View.VISIBLE);
                            mPresenter.getPicture(category.getLinkUrl(),false);
                            mCategory.setText(category.getName());
                            mDialog.dismiss();
                        }else {
                            Toast.makeText(PictureActivity.this,"获取失败，请重试",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        })
                .setLayoutRes(R.layout.layout_picture_select)
                .setDimAmount(0.6f)
                .show();
    }
}
