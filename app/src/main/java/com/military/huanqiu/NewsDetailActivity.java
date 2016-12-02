package com.military.huanqiu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.military.R;
import com.military.bean.NewsBean;
import com.military.huanqiu.persenter.NewsDetailPresenter;
import com.military.huanqiu.view.NewsDetailView;
import com.military.listener.AppBarStateChangeListener;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public class NewsDetailActivity extends AppCompatActivity implements NewsDetailView{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.imgDetail)
    ImageView mImgDetail;
    @BindView(R.id.text_content)
    TextView mContent;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.frame)
    PtrClassicFrameLayout mFrame;

    private ArrayList<String> mArrayImgs = new ArrayList<>();
    private static final int MSG_GET_CONTENT_SUCCESSFUL = 0;
    private static final int MSG_GET_IMG_SUCCESSFUL = 1;
    private NewsDetailPresenter mPresenter;
    private DetailHandler mHandler = new DetailHandler(this);

    private static class DetailHandler extends Handler {
        private WeakReference<NewsDetailActivity> ref;
        private NewsDetailActivity act;

        DetailHandler(NewsDetailActivity activity){
            ref = new WeakReference<>(activity);
            act = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_CONTENT_SUCCESSFUL:
                    if (act.mFrame != null)
                        act.mFrame.refreshComplete();
                    String content = (String) msg.obj;
                    if (content != null) {
                        Log.d("handleMessage"," content--"  + content);
                        act.mContent.setText(content);
                    }
                    break;
                case MSG_GET_IMG_SUCCESSFUL:
                    if (act.mArrayImgs.size() != 0) {
                        Picasso.with(act).load(act.mArrayImgs.get(0)).into(act.mImgDetail);
                    }

                    break;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        final NewsBean newsBean = (NewsBean) getIntent().getSerializableExtra("newsBean");
        if (newsBean != null) {
            Log.d("newsBean"," toString--" + newsBean.toString());
        }
        setSupportActionBar(mToolbar);
        setAllListener();

        if (newsBean != null) {
            mPresenter = new NewsDetailPresenter(this,this);
            mPresenter.getDetailInfo(newsBean.getLinkUrl());
            mToolbarLayout.setTitle(newsBean.getTitle());
        }else {
            Toast.makeText(this,"get detail fail",Toast.LENGTH_SHORT).show();
            finish();
        }

        StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, 60, 0, 60);
        header.initWithString("English");
        header.setBackgroundColor(getResources().getColor(R.color.black));
        header.setTextColor(getResources().getColor(R.color.white));

        mFrame.setDurationToCloseHeader(1500);
        mFrame.setHeaderView(header);
        mFrame.addPtrUIHandler(header);

        mFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (newsBean != null && !TextUtils.isEmpty(newsBean.getLinkUrl())) {
                    mPresenter.getDetailInfo(newsBean.getLinkUrl());
                }
            }
        });
        mAppBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if( state == State.EXPANDED ) {//展开状态
                    mFrame.setEnabled(true);

                }else if(state == State.COLLAPSED){//折叠状态
                    mFrame.setEnabled(false);

                }else {//中间状态
                    mFrame.setEnabled(false);

                }
            }
        });
    }

    private void setAllListener() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void setDetailContent(String content) {
        if (mHandler != null) {
            mHandler.obtainMessage(MSG_GET_CONTENT_SUCCESSFUL,content).sendToTarget();

        }
    }

    @Override
    public void setImgs(ArrayList<String> arrayList) {
        mArrayImgs.clear();
        mArrayImgs.addAll(arrayList);
        if (mHandler != null) {
            mHandler.sendEmptyMessage(MSG_GET_IMG_SUCCESSFUL);
        }
    }
}
