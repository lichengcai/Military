package com.military.picture.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.military.R;
import com.military.bean.CategoryBean;
import com.military.huanqiu.adapter.ViewHolder;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/12/9.
 */

public class PictureSelectAdapter extends BaseAdapter {
    private ArrayList<CategoryBean> mData;
    private Context mContext;

    public PictureSelectAdapter(Context context) {
        this.mContext = context;
        mData = new ArrayList<>();
        mData.add(new CategoryBean("图讯","http://photo.huanqiu.com/photoview/"));
        mData.add(new CategoryBean("囧图","http://photo.huanqiu.com/funnypicture/"));
//        mData.add(new CategoryBean("独家","http://photo.huanqiu.com/exclusivegallery/?"));
        mData.add(new CategoryBean("视界","http://photo.huanqiu.com/sight/"));
//        mData.add(new CategoryBean("壹周","http://photo.huanqiu.com/weekinpicture/?"));
        mData.add(new CategoryBean("世相","http://photo.huanqiu.com/photostory/"));
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null ) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_picture_select,parent,false);
        }

        TextView textView = ViewHolder.get(convertView,R.id.text_pic_select);
        textView.setText(mData.get(position).getName());
        return convertView;
    }

    public CategoryBean getCategory(int position) {

        return mData.get(position);
    }
}
