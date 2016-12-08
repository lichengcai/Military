package com.military.picture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.military.R;
import com.military.bean.Video;
import com.military.picture.presenter.PicturePresenter;
import com.military.picture.view.PictureView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureActivity extends AppCompatActivity implements PictureView{
    @BindView(R.id.recyclerPic)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    LinearLayout mLayoutLoading;

    private PicturePresenter mPresenter;
    private String url = "http://photo.huanqiu.com/funnypicture/?";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        mPresenter = new PicturePresenter(this,this);
        mPresenter.getPicture(url,false);
    }

    @Override
    public void setPicture(ArrayList<Video> arrayList,boolean loadMore) {

    }
}
