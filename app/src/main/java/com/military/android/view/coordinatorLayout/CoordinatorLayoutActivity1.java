package com.military.android.view.coordinatorLayout;

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

public class CoordinatorLayoutActivity1 extends BaseActivity {
    @BindView(R.id.button)
    Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator1);
        ButterKnife.bind(this);

        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    view.setX(motionEvent.getX() - view.getWidth()/2);
                    view.setY(motionEvent.getY() - view.getHeight()/2);
                }
                return true;
            }
        });

    }
}
