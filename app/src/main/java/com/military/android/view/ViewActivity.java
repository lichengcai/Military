package com.military.android.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.military.R;
import com.military.military.ui.activity.BaseActivity;

/**
 * Created by lichengcai on 2017/8/9.
 */

public class ViewActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }
}
