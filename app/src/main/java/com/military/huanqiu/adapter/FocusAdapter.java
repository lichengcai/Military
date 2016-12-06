package com.military.huanqiu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.military.R;
import com.military.bean.NewsBean;
import com.military.listener.OnItemClickListener;
import com.military.transforms.CubeOutTransformer;
import com.military.transforms.TransformerItem;
import com.military.widget.convenientbanner.ConvenientBanner;
import com.military.widget.convenientbanner.holder.CBViewHolderCreator;
import com.military.widget.convenientbanner.holderview.NetworkImageHolderView;

import java.util.ArrayList;

/**
 * Created by chengcai on 2016/11/24.
 */

public class FocusAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM =0;
    private static final int TYPE_HEADER = 1;
    private Context mContext;
    private ArrayList<NewsBean> mDataList;
    private ArrayList<NewsBean> mDataFocus;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public FocusAdapter(Context context, ArrayList<NewsBean> mDataFocus, ArrayList<NewsBean> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
        this.mDataFocus = mDataFocus;
    }

    public NewsBean getListItem(int position) {
        return mDataList.get(position);
    }

    public NewsBean getBannerItem(int position) {
        return mDataFocus.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderHolder(LayoutInflater.from(mContext).inflate(R.layout.heander_focus,parent,false));
        }else {
            return new FocusListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_focus_list,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FocusListHolder) {
            ((FocusListHolder) holder).title.setText(mDataList.get(position).getTitle());
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
                    }
                });
            }
        }
        if (holder instanceof HeaderHolder) {
            ((HeaderHolder) holder).mBanner.setPages(new CBViewHolderCreator() {
                        @Override
                        public Object createHolder() {
                            return new NetworkImageHolderView();
                        }
                    },mDataFocus);
            ((HeaderHolder) holder).mBanner.startTurning(5000);//设置轮播开始自动循环
            ((HeaderHolder) holder).mBanner.setScrollDuration(2000);//设置滑动速度
            try {
                ((HeaderHolder) holder).mBanner.getViewPager().setPageTransformer(true,new TransformerItem(CubeOutTransformer.class).clazz.newInstance());//设置轮播动画
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }else {
            return TYPE_ITEM;
        }
    }

    private class FocusListHolder extends RecyclerView.ViewHolder {

        private TextView title;
        FocusListHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text_title);
        }
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {
        ConvenientBanner mBanner;

        HeaderHolder(View itemView){
            super(itemView);
            mBanner = (ConvenientBanner) itemView.findViewById(R.id.bannerFocus);
        }
    }
}
