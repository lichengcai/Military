package com.military.huanqiu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.military.R;
import com.military.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by chengcai on 2016/11/24.
 */

public class FocusListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<NewsBean> mData;

    public FocusListAdapter(Context context,ArrayList<NewsBean> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FocusListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_focus_list,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FocusListHolder) {
            ((FocusListHolder) holder).title.setText(mData.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class FocusListHolder extends RecyclerView.ViewHolder {

        private TextView title;
        FocusListHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text_title);
        }
    }
}
