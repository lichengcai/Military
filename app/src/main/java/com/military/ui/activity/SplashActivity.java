package com.military.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.military.R;
import com.military.guide.GuideActivity;
import com.military.widget.particleview.ParticleView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/24.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.pv_1)
    ParticleView mParticleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        //执行动画
        mParticleView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mParticleView.startAnim();
            }
        }, 1000);

        //动画监听
        mParticleView.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                SplashActivity.this.finish();
            }
        });
    }
}
