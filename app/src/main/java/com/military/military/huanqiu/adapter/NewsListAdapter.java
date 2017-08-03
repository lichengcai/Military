package com.military.military.huanqiu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.military.R;
import com.military.military.bean.NewsBean;
import com.military.military.huanqiu.FooterHolder;
import com.military.military.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/11/28.
 */

public class NewsListAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM =0;
    private static final int TYPE_FOOTER = 1;
    private Context mContext;
    private ArrayList<NewsBean> mData;
    private OnItemClickListener onItemClickListener;
    private int mLastItem = -1;
    private boolean mShowFooter = true;


    public void loadMore(ArrayList<NewsBean> arrayList) {
        mData.addAll(arrayList);
        notifyDataSetChanged();
    }

    public void setIsShowFooter(boolean mShowFooter) {
        this.mShowFooter = mShowFooter;
    }

    public boolean isShowFooter() {
        return mShowFooter;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public NewsListAdapter(Context context,ArrayList<NewsBean> arrayList) {
        this.mContext = context;
        this.mData =arrayList;
    }

    public NewsBean getItem(int position) {
        return mData.get(position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_FOOTER) {
            return new FooterHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_footer,parent,false));
        }else {
            return new NewsListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news_list,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsListHolder) {
//            if (holder.getAdapterPosition() > mLastItem) {
//                Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.item_slide_in_bottom);
//                holder.itemView.setAnimation(animation);
//                mLastItem = holder.getAdapterPosition();
//            }

            NewsBean newsBean = mData.get(position);
            ((NewsListHolder) holder).text_detail_title.setText(newsBean.getDetailTitle());
            ((NewsListHolder) holder).text_title.setText(newsBean.getTitle());
            ((NewsListHolder) holder).text_time.setText(newsBean.getTime());

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        int count = mShowFooter ? 1 : 0;
        return mData.size() + count;
    }

    @Override
    public int getItemViewType(int position) {
        if (!mShowFooter)
            return TYPE_ITEM;
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        }else {
            return TYPE_ITEM;
        }
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
