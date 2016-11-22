package com.military.guide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.military.R;
import com.military.bean.GuideBean;
import com.military.listener.OnItemClickListener;
import com.military.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/11/18.
 */

public class GuideListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<GuideBean> mData;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public GuideListAdapter(Context mContext,ArrayList<GuideBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GuideHolder(LayoutInflater.from(mContext).inflate(R.layout.item_guide,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GuideHolder) {
            GuideBean guideBean = mData.get(position);
            ((GuideHolder) holder).textName.setText(guideBean.getName());
            Picasso.with(mContext).load(guideBean.getImgUrl()).into(((GuideHolder) holder).imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class GuideHolder extends RecyclerView.ViewHolder {
        private CircleImageView imageView;
        private TextView textName;

        private GuideHolder(View itemView) {
            super(itemView);
            imageView = (CircleImageView) itemView.findViewById(R.id.profile_image);
            textName = (TextView) itemView.findViewById(R.id.text_name);
        }
    }
}
