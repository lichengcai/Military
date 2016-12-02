package com.military.huanqiu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.military.R;
import com.military.bean.WeaponBean;
import com.military.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/12/2.
 */

public class WeaponListAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<WeaponBean> mData;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public WeaponListAdapter(Context context, ArrayList<WeaponBean> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WeaponListHolder(LayoutInflater.from(mContext).inflate(R.layout.item_weapon_list,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof WeaponListHolder) {
            WeaponBean weaponBean = mData.get(position);
            Picasso.with(mContext).load(weaponBean.getImgUrl()).into(((WeaponListHolder) holder).img_weapon);
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
        return mData.size();
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
