package com.military.military.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import com.military.R;
import com.military.guide.GuideActivity;
import com.military.military.widget.wowsplash.WowSplashView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/24.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.wowView)
    WowSplashView mWowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);



        mWowView.startAnimate();
        mWowView.setOnEndListener(new WowSplashView.OnEndListener() {
            @Override
            public void onEnd(WowSplashView wowSplashView) {
                startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                finish();
            }
        });
    }
}
