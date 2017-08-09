package com.military.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.military.R;
import com.military.android.view.CanvasActivity;
import com.military.military.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2017/8/3.
 */

public class AndroidActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.button_view)
    Button mBtn_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        ButterKnife.bind(this);


        setAllListener();
    }

    private void setAllListener() {
        mBtn_view.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_view:
                startActivity(new Intent(AndroidActivity.this, CanvasActivity.class));
                break;
        }
    }
}
