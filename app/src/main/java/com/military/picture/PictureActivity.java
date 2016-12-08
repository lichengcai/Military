package com.military.picture;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.military.R;
import com.military.bean.Video;
import com.military.picture.adapter.PictureAdapter;
import com.military.picture.presenter.PicturePresenter;
import com.military.picture.view.PictureView;
import com.military.ui.activity.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureActivity extends BaseActivity implements PictureView{
    @BindView(R.id.recyclerPic)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    LinearLayout mLayoutLoading;
    @BindView(R.id.text_category)
    TextView mCategory;
    @BindView(R.id.img_select)
    ImageView mImgSelect;

    private PicturePresenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private PictureAdapter mAdapter;
    private String url = "http://photo.huanqiu.com/funnypicture/?";
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
                    act.mRecyclerView.setAdapter(act.mAdapter);
                    break;
                case MSG_GET_PICTURE_DATA_MORE:

                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        mPresenter = new PicturePresenter(this,this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.getPicture(url,false);
    }

    @Override
    public void setPicture(ArrayList<Video> arrayList,boolean loadMore) {
        if (arrayList != null) {
            mAdapter = new PictureAdapter(mContext,arrayList);

            if (mHandler != null) {
                mHandler.sendEmptyMessage(MSG_GET_PICTURE_DATA_SUCCESS);
            }
        }
    }
}
