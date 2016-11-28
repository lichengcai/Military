package com.military.huanqiu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.military.R;
import com.military.bean.NewsBean;
import com.military.huanqiu.persenter.NewsDetailPresenter;
import com.military.huanqiu.view.NewsDetailView;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        NewsBean newsBean = (NewsBean) getIntent().getSerializableExtra("newsBean");

        setSupportActionBar(mToolbar);
        setAllListener();

        if (newsBean != null) {
            mPresenter = new NewsDetailPresenter(this);
            mPresenter.getDetailInfo(newsBean.getLinkUrl());
            mToolbarLayout.setTitle(newsBean.getTitle());
        }else {
            Toast.makeText(this,"get detail fail",Toast.LENGTH_SHORT).show();
            finish();
        }

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
