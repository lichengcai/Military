package com.military.military.picture.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.military.military.bean.Picture;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/12/9.
 */

public class PicDetailAdapter extends PagerAdapter {
    private ArrayList<View> mData;

    public PicDetailAdapter(ArrayList<View> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        // TODO Auto-generated method stub
        container.removeView(mData.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        container.addView(mData.get(position));
        return mData.get(position);
    }
}
