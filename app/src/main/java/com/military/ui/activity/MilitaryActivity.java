package com.military.ui.activity;

import android.os.Bundle;

import com.military.R;
import com.military.bean.Test;
import com.military.widget.convenientbanner.ConvenientBanner;
import com.military.widget.convenientbanner.holder.CBViewHolderCreator;
import com.military.widget.convenientbanner.holderview.NetworkImageHolderView;

import java.util.ArrayList;

public class MilitaryActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Test> array = new ArrayList<>();
        array.add(new Test("titleOne","http://himg2.huanqiu.com/attachment2010/2016/1118/09/26/20161118092625438.jpg"));
        array.add(new Test("titleTwo","http://himg2.huanqiu.com/attachment2010/2016/1118/08/50/20161118085035979.jpg"));
        array.add(new Test("titleThree","http://himg2.huanqiu.com/attachment2010/2016/1118/09/46/20161118094614987.jpg"));
        ConvenientBanner banner = (ConvenientBanner) findViewById(R.id.banner);
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();//guide
            }
        },array);

        banner.startTurning(5000);//设置轮播开始自动循环
        banner.setScrollDuration(2000);//设置滑动速度
    }
}
