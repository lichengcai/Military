package com.military.huanqiu.weapon;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.military.MilitaryApplication;
import com.military.R;
import com.military.huanqiu.FragmentFocus;
import com.military.huanqiu.FragmentNewsList;
import com.military.ui.activity.BaseActivity;
import com.military.video.adapter.VideoAdapter;
import com.military.video.adapter.VideoPagerAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeaponDetailActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private VideoPagerAdapter mAdapter;

    private MyHandler mHandler  = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<WeaponDetailActivity> ref;
        private WeaponDetailActivity act;

        public MyHandler(WeaponDetailActivity activity) {
            ref = new WeakReference<>(activity);
            act = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Document document = (Document) msg.obj;
                    Elements elements = document.select("div.intron");
                    Log.d("elements","elements--" + elements);
                    Elements elements1 = document.select("div.otherList");
                    for (Element element : elements1) {
                        Log.d("elements","name--" + element.select("h3.title_").get(0).text());
                    }
                    Log.d("elements","elements1==" + elements1.size() + "\n " + elements1.get(0).text());

                    break;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_detail);
        ButterKnife.bind(this);

        test();


        MilitaryApplication.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://weapon.huanqiu.com/j_16").get();//view-source:http://weapon.huanqiu.com/y_20
                    mHandler.obtainMessage(0,document).sendToTarget();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void test() {
        mAdapter = new VideoPagerAdapter(getSupportFragmentManager());
        mAdapter.addFragment(FragmentNewsList.newInstance("http://mil.huanqiu.com/china/"),"China");
        mAdapter.addFragment(FragmentNewsList.newInstance("http://mil.huanqiu.com/world/"),"World");
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
