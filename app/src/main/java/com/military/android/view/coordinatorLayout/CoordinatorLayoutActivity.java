package com.military.android.view.coordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.military.R;
import com.military.military.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2017/8/9.
 */

public class CoordinatorLayoutActivity extends BaseActivity {
    @BindView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        ButterKnife.bind(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CoordinatorLayoutActivity.this,CoordinatorLayoutActivity1.class));
            }
        });

    }
}
