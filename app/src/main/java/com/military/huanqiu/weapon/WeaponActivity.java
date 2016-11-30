package com.military.huanqiu.weapon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ExpandableListView;

import com.military.R;
import com.military.ui.activity.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/29.
 */

public class WeaponActivity extends BaseActivity {
    @BindView(R.id.list_category)
    ExpandableListView mListCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon);
        ButterKnife.bind(this);


    }
}
