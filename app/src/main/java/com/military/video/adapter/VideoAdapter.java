package com.military.video.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.military.R;
import com.military.bean.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by lichengcai on 2016/11/23.
 */

public class VideoAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Video> mData;

    public VideoAdapter(Context context,ArrayList<Video> arrayList) {
        this.mContext = context;
        this.mData = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoHolder(LayoutInflater.from(mContext).inflate(R.layout.item_video,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VideoHolder) {
            Video video = mData.get(position);
            ((VideoHolder) holder).playerStandard.setUp(video.getVideoUrl(),JCVideoPlayer.SCREEN_LAYOUT_LIST,video.getTitle());
            ((VideoHolder) holder).playerStandard.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(mContext).load(video.getImgUrl()).into(((VideoHolder) holder).playerStandard.thumbImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class VideoHolder extends RecyclerView.ViewHolder {
        JCVideoPlayerStandard playerStandard;

        VideoHolder(View itemView) {
            super(itemView);
            playerStandard = (JCVideoPlayerStandard) itemView.findViewById(R.id.videoPlayer);
        }
    }
}
