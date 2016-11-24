package com.military.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.military.bean.Channel;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/17.
 */

public class BaseActivity extends FragmentActivity {
    protected BaseActivity mContext;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity"," BaseActivity onCreate");
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public int dip2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int sp2px(float spValue) {
        final float fontScale = this.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public int px2dip( float pxValue) {
        final float scale = this.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    public int px2sp(float spValue) {
        final float fontScale = this.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue/fontScale + 0.5f);
    }
}
