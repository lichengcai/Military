package com.military.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.military.R;
import com.military.android.view.ViewActivity;
import com.military.android.view.canvas.CanvasActivity;
import com.military.android.view.coordinatorLayout.CoordinatorLayoutActivity;
import com.military.military.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2017/8/3.
 */

public class AndroidActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.button1)
    Button mBtn_view;
    @BindView(R.id.button2)
    Button mBtn_coordinator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        ButterKnife.bind(this);


        setAllListener();
    }

    private void setAllListener() {
        mBtn_view.setOnClickListener(this);
        mBtn_coordinator.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(new Intent(AndroidActivity.this, ViewActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(AndroidActivity.this, CoordinatorLayoutActivity.class));
                break;
        }
    }
}
