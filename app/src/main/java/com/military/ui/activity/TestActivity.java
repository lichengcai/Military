package com.military.ui.activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.military.R;
import com.squareup.picasso.Picasso;
import com.wenen.gridimageview.GridImageView;
import com.wenen.gridimageview.LoadImageCallBack;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/16.
 */

public class TestActivity extends BaseActivity implements LoadImageCallBack{
    @BindView(R.id.images)
    GridImageView gridImageView;
    @BindView(R.id.expanded_image)
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        ArrayList<String> list = new ArrayList<>();
        list.add("http://images.koolearn.com/kooupload/201612111203_1481428990160485.jpg");
        list.add("http://images.koolearn.com/kooupload/201612111203_1481428990160485.jpg");
        list.add("http://images.koolearn.com/kooupload/201612111203_1481428990160485.jpg");
        list.add("http://images.koolearn.com/kooupload/201612111203_1481428990160485.jpg");
        list.add("http://images.koolearn.com/kooupload/201612111203_1481428990160485.jpg");
        list.add("http://images.koolearn.com/kooupload/201612111203_1481428990160485.jpg");
        list.add("http://images.koolearn.com/kooupload/201612111203_1481428990160485.jpg");
        list.add("http://images.koolearn.com/kooupload/201612111203_1481428990160485.jpg");

        gridImageView.setImage(list,this);

//        Picasso.with(this).load("http://himg2.huanqiu.com/attachment2010/2016/1207/09/35/20161207093512548.jpg").into(photoView);
        // 启用图片缩放功能
    }


    @Override
    public void loadImage(ImageView view, String url) {
        Picasso.with(TestActivity.this).load(url).into(view);
    }

    @Override
    public void onClickResponse(ImageView view, String url) {
        Picasso.with(TestActivity.this).load(url).into(imageView);
        gridImageView.zoomImageFromThumb(view, imageView,
                (FrameLayout) findViewById(R.id.activity_main));

    }
}
