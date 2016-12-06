package com.military.huanqiu.weapon;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.military.R;
import com.military.bean.WeaponBean;
import com.military.huanqiu.FragmentNewsList;
import com.military.huanqiu.FragmentWeaponDetail;
import com.military.huanqiu.adapter.CommonTabPagerAdapter;
import com.military.huanqiu.model.NewsDetailModelImpl;
import com.military.listener.OnLoadingListener;
import com.military.ui.activity.BaseActivity;
import com.squareup.picasso.Picasso;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.lang.ref.WeakReference;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeaponDetailActivity extends BaseActivity implements CommonTabPagerAdapter.TabPagerListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.ad_view)
    ImageView mImage;

    private CommonTabPagerAdapter adapter;

    public static Document mDocument = null;
    private static final int MSG_GET_DETAIL_SUCCESS = 0;
    private static final int MSG_GET_DETAIL_FAIL = 1;

    private WeaponDetailHandler mHandler = new WeaponDetailHandler(this);

    private static class WeaponDetailHandler extends Handler {
        private WeakReference<WeaponDetailActivity> ref;
        private WeaponDetailActivity act;

        WeaponDetailHandler(WeaponDetailActivity activity) {
            ref = new WeakReference<>(activity);
            act = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_GET_DETAIL_SUCCESS:
                    Document document = (Document) msg.obj;
                    mDocument = document;
                    if (document != null) {
                        Elements elements = document.select("div.maxPic");
                        String imgUrl = elements.get(0).select("img").get(0).attr("src");
                        Picasso.with(act.mContext).load(imgUrl).into(act.mImage);
                        act.adapter = new CommonTabPagerAdapter(act.getSupportFragmentManager()
                                , 3, Arrays.asList("简介", "详情", "参数"), act);
                        act.adapter.setListener(act);
                        act.viewpager.setAdapter(act.adapter);
                        act.tabLayout.setupWithViewPager(act.viewpager);
                        act.tabLayout.setTabMode(TabLayout.MODE_FIXED);
                    }
                    break;
                case MSG_GET_DETAIL_FAIL:
                    act.finish();
                    Toast.makeText(act,"获取详情失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_detail);
        ButterKnife.bind(this);


        WeaponBean weaponBean = (WeaponBean) getIntent().getSerializableExtra("weaponBean");
        if (weaponBean != null) {
            Picasso.with(this).load(weaponBean.getImgUrl()).into(mImage);
//            setTitle(weaponBean.getName());
            collapsingToolbar.setTitle(weaponBean.getName());
            collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的
            getDetailDocument(weaponBean);
        }else {
            finish();
            Toast.makeText(this,"获取详情失败",Toast.LENGTH_SHORT).show();
        }



    }
    private void getDetailDocument(WeaponBean weaponBean) {
        NewsDetailModelImpl mDocumentDetail = new NewsDetailModelImpl(this);
        mDocumentDetail.getDetailInfo(weaponBean.getLinkUrl(), new OnLoadingListener() {
            @Override
            public void onSuccess(Document json) {
                if (mHandler != null) {
                    mHandler.obtainMessage(MSG_GET_DETAIL_SUCCESS,json).sendToTarget();
                }
            }

            @Override
            public void onFail() {
                if (mHandler != null) {
                    mHandler.sendEmptyMessage(MSG_GET_DETAIL_FAIL);
                }
            }

            @Override
            public void onEmpty() {

            }

            @Override
            public void onLoading() {

            }
        });
    }
    @Override
    public Fragment getFragment(int position) {
        if (position == 0) {
            return new FragmentSummary();
        }else if (position == 1){
            return new FragmentWeaponDetail();
        }else {
            return new FragmentParameter();
        }
    }
}
