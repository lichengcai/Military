package com.military.huanqiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.military.R;
import com.military.bean.CategoryBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chengcai on 2016/11/30.
 */

public class CategoryAdapter extends BaseExpandableListAdapter {
    private ArrayList<String> mGroupData = new ArrayList<>();
    private ArrayList<ArrayList<CategoryBean>> mChildData = new ArrayList<>();
    private Context mContext;

    public CategoryAdapter(Context context,Map<String,ArrayList<CategoryBean>> mData) {
        this.mContext = context;
        for (String key : mData.keySet()) {
            mGroupData.add(key);
            mChildData.add(mData.get(key));
        }

    }

    @Override
    public int getGroupCount() {
        return mGroupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_category_group,parent,false);
        }
        TextView textView = ViewHolder.get(convertView,R.id.text_group);
        ImageView imageView = ViewHolder.get(convertView,R.id.image);

        textView.setText(mGroupData.get(groupPosition));

        if (isExpanded) {
            imageView.setImageResource(R.drawable.up_arrow);
        }else {
            imageView.setImageResource(R.drawable.down_arrow);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_category_child,parent,false);
        }
        TextView textView = ViewHolder.get(convertView,R.id.text_child);
        textView.setText(mChildData.get(groupPosition).get(childPosition).getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
