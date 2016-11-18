package com.military;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        Glide.with(this).load("http://himg2.huanqiu.com/attachment2010/2016/1118/08/50/20161118085035979.jpg").into(imageView);
    }
}
