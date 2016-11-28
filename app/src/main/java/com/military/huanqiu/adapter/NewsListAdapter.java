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
 * Created by lichengcai on 2016/11/28.
 */

public class NewsListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<NewsBean> mData;

    public NewsListAdapter(Context context,ArrayList<NewsBean> arrayList) {
        this.mContext = context;
        this.mData =arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news_list,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsListHolder) {
            NewsBean newsBean = mData.get(position);
            ((NewsListHolder) holder).text_detail_title.setText(newsBean.getDetailTitle());
            ((NewsListHolder) holder).text_title.setText(newsBean.getTitle());
            ((NewsListHolder) holder).text_time.setText(newsBean.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class NewsListHolder extends RecyclerView.ViewHolder {
        TextView text_title;
        TextView text_detail_title;
        TextView text_time;

        NewsListHolder(View itemView) {
            super(itemView);
            text_detail_title = (TextView) itemView.findViewById(R.id.textDetailTitle);
            text_time = (TextView) itemView.findViewById(R.id.textTime);
            text_title = (TextView) itemView.findViewById(R.id.textTitle);
        }
    }
}
