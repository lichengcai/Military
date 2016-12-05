package com.military.huanqiu.weapon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.military.R;
import com.military.bean.CategoryBean;
import com.military.bean.WeaponBean;
import com.military.huanqiu.adapter.CategoryAdapter;
import com.military.huanqiu.adapter.WeaponListAdapter;
import com.military.huanqiu.persenter.WeaponPresenter;
import com.military.huanqiu.view.WeaponView;
import com.military.listener.OnItemClickListener;
import com.military.ui.activity.BaseActivity;
import com.military.utils.FileUtils;
import com.military.widget.customdialog.BottomDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lichengcai on 2016/11/29.
 */

public class WeaponActivity extends AppCompatActivity implements WeaponView{
    @BindView(R.id.recyclerWeaponList)
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_loading)
    LinearLayout mLayoutLoading;
    @BindView(R.id.text_category)
    TextView mTextCategory;
    @BindView(R.id.img_select)
    ImageView mImgSelect;

    private ExpandableListView mListCategory;

    private String url = "http://weapon.huanqiu.com/weaponlist/aircraft/list_1_0";
    //http://weapon.huanqiu.com/weaponlist/aircraft/list_1_0
    //http://weapon.huanqiu.com/weaponlist/aircraft/list_1_0_0_0_2
    //http://weapon.huanqiu.com/weaponlist/aircraft/list_1_1_0_0_2
    private WeaponPresenter mPresenter;
    private WeaponListAdapter mAdapter;
    private CategoryAdapter mCategoryAdapter;
    private LinearLayoutManager mLayoutManager;
    private WeaponHandler mHandler = new WeaponHandler(this);
    private static final int MSG_GET_WEAPON_DATA = 0;
    private static final int MSG_GET_WEAPON_MORE = 1;


    private static class WeaponHandler extends Handler {
        private WeakReference<WeaponActivity> ref;
        private WeaponActivity act;

        WeaponHandler(WeaponActivity activity) {
            ref = new WeakReference<>(activity);
            act = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_WEAPON_DATA:
                    act.mLayoutManager = new GridLayoutManager(act,2);
                    act.mRecyclerView.setLayoutManager(act.mLayoutManager);
                    act.mRecyclerView.setAdapter(act.mAdapter);
                    if (act.mLayoutLoading != null){
                        act.mLayoutLoading.setVisibility(View.GONE);
                    }

                    act.mAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(act,WeaponDetailActivity.class);
                            intent.putExtra("weaponBean",act.mAdapter.getItem(position));

                            act.startActivity(intent);
                        }
                    });
                    break;
                case MSG_GET_WEAPON_MORE:
                    ArrayList<WeaponBean> array = (ArrayList<WeaponBean>) msg.obj;
                    act.mAdapter.loadMore(array);
                    break;
            }
        }
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);

        mPresenter = new WeaponPresenter(this,this);

        mCategoryAdapter = new CategoryAdapter(this,CategoryBean.getCategoryData());
        CategoryBean categoryBean = mCategoryAdapter.getCategoryBean(0,0);

        url = categoryBean.getLinkUrl();
        mPresenter.getWeaponListData(url,false);
        mTextCategory.setText(categoryBean.getName());

        setAllListener();

    }

    private void setAllListener() {
        mImgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelect();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && mAdapter.isShowFooter()) {
                    mPresenter.getWeaponListData(url,true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


    private void setSelect() {
        final BottomDialog mDialog = BottomDialog.create(getSupportFragmentManager());

        mDialog.setViewListener(new BottomDialog.ViewListener() {
            @Override
            public void bindView(View v) {
                mListCategory = (ExpandableListView) v.findViewById(R.id.mListCategory);
                mListCategory.setGroupIndicator(null);
                mListCategory.setAdapter(mCategoryAdapter);

                mListCategory.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        if (mLayoutLoading != null)
                            mLayoutLoading.setVisibility(View.VISIBLE);
                        mTextCategory.setText(mCategoryAdapter.getCategoryBean(groupPosition,childPosition).getName());
                        url = mCategoryAdapter.getCategoryBean(groupPosition,childPosition).getLinkUrl();
                        mPresenter.getWeaponListData(url,false);
                        mDialog.disMiss();
                        return true;
                    }
                });

                mListCategory.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                    @Override
                    public void onGroupExpand(int groupPosition) {
                        for (int i = 0; i < mCategoryAdapter.getGroupCount(); i++) {
                            if (i != groupPosition) {
                                mListCategory.collapseGroup(i);
                            }
                        }
                    }
                });
            }
        })
                .setLayoutRes(R.layout.layout_test)
                .setDimAmount(0.6f)
                .setTag("BottomDialog")
                .show();
    }

    @Override
    public void setWeaponList(ArrayList<WeaponBean> arrayList,boolean loadMore) {

        if (mHandler != null) {

            if (loadMore) {
                mHandler.obtainMessage(MSG_GET_WEAPON_MORE,arrayList).sendToTarget();

            }else {
                mAdapter = new WeaponListAdapter(WeaponActivity.this,arrayList);
                mHandler.sendEmptyMessage(MSG_GET_WEAPON_DATA);
            }

            if (arrayList.size() == 0) {
                mAdapter.setIsShowFooter(false);
            }else {
                mAdapter.setIsShowFooter(true);
            }
        }

    }
}
