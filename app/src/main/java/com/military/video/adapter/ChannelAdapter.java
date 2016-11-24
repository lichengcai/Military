package com.military.video.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.military.R;
import com.military.bean.Channel;
import com.military.listener.OnItemClickListener;
import com.military.video.ChannelActivity;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/11/22.
 */

public class ChannelAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Channel> mData;
    private OnItemClickListener onItemClickListener;
    private boolean flag = false;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Channel getItem(int position) {
        return mData.get(position);
    }

    public void addItem() {
            flag = true;
            notifyDataSetChanged();
    }

    public ChannelAdapter(Context mContext,ArrayList<Channel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyChannelHolder(LayoutInflater.from(mContext).inflate(R.layout.item_channel,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyChannelHolder) {

            Channel channel = mData.get(position);
            ((MyChannelHolder) holder).name.setText(channel.getName());

            ((MyChannelHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(((MyChannelHolder) holder).itemView,holder.getLayoutPosition());
                    }
                }
            });

            if (flag && position == mData.size()-1) {
                Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.channel_add_ani);
                holder.itemView.startAnimation(animation);
                flag = false;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class MyChannelHolder extends RecyclerView.ViewHolder {

        private TextView name;
        public MyChannelHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_channel_name);
        }
    }
}
