package com.military.picture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.military.R;
import com.military.bean.Video;
import com.military.bean.WeaponBean;
import com.military.huanqiu.FooterHolder;
import com.military.huanqiu.adapter.WeaponListAdapter;
import com.military.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lichengcai on 2016/12/8.
 */

public class PictureAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM =0;
    private static final int TYPE_FOOTER = 1;
    private boolean mShowFooter = true;
    private Context mContext;
    private ArrayList<Video> mData;
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

    public PictureAdapter(Context context,ArrayList<Video> arrayList) {
        this.mContext = context;
        this.mData = arrayList;
    }
    public void loadMore(ArrayList<Video> arrayList) {
        mData.addAll(arrayList);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new FooterHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_footer,parent,false));
        }else {
            return new PictureHolder(LayoutInflater.from(mContext).inflate(R.layout.item_picture,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PictureHolder) {
            Video video = mData.get(position);
            Picasso.with(mContext).load(video.getImgUrl()).into(((PictureHolder) holder).imageView);
            ((PictureHolder) holder).title.setText(video.getTitle());

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

    private class PictureHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;

        public PictureHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_picture);
            title = (TextView) itemView.findViewById(R.id.title_pic);
        }
    }
}
