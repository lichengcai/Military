package com.military.huanqiu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.military.R;
import com.military.bean.WeaponBean;
import com.military.huanqiu.FooterHolder;
import com.military.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/12/2.
 */

public class WeaponListAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM =0;
    private static final int TYPE_FOOTER = 1;
    private boolean mShowFooter = true;
    private Context mContext;
    private ArrayList<WeaponBean> mData;
    private OnItemClickListener onItemClickListener;

    public void setIsShowFooter(boolean mShowFooter) {
        this.mShowFooter = mShowFooter;
    }

    public boolean isShowFooter() {
        return mShowFooter;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public WeaponListAdapter(Context context, ArrayList<WeaponBean> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    public void loadMore(ArrayList<WeaponBean> arrayList) {
        mData.addAll(arrayList);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new FooterHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_footer,parent,false));
        }else {
            return new WeaponListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_weapon_list,parent,false));
        }

    }

    public WeaponBean getItem(int position) {
        return mData.get(position);
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WeaponListHolder) {
            WeaponBean weaponBean = mData.get(position);
            if (weaponBean.getImgUrl() != null)
                Picasso.with(mContext).load(weaponBean.getImgUrl()).into(((WeaponListHolder) holder).img_weapon);
            if (weaponBean.getContryImg()!=null)
                Picasso.with(mContext).load(weaponBean.getContryImg()).into(((WeaponListHolder) holder).img_country);

            ((WeaponListHolder) holder).text_weapon_name.setText(weaponBean.getName());
            ((WeaponListHolder) holder).text_country_name.setText(weaponBean.getCountryName());
            ((WeaponListHolder) holder).text_describe.setText(weaponBean.getDescribe());
            ((WeaponListHolder) holder).text_category.setText(weaponBean.getCategory());

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

    private class WeaponListHolder extends RecyclerView.ViewHolder {
        ImageView img_weapon;
        TextView text_weapon_name;
        ImageView img_country;
        TextView text_country_name;
        TextView text_describe;
        TextView text_category;

        WeaponListHolder(View itemView) {
            super(itemView);
            img_weapon = (ImageView) itemView.findViewById(R.id.img_weapon);
            text_weapon_name = (TextView) itemView.findViewById(R.id.text_weapon_name);
            img_country = (ImageView) itemView.findViewById(R.id.img_country);
            text_country_name = (TextView) itemView.findViewById(R.id.text_country_name);
            text_describe = (TextView) itemView.findViewById(R.id.text_describe);
            text_category = (TextView) itemView.findViewById(R.id.text_category);
        }
    }
}
