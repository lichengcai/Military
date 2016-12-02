package com.military.video.presenter;

import android.app.Activity;
import android.util.Log;

import com.military.bean.Video;
import com.military.listener.OnLoadingListener;
import com.military.video.model.VideoModel;
import com.military.video.model.VideoModelImpl;
import com.military.video.view.VideoView;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;


/**
 * Created by lichengcai on 2016/11/23.
 */

public class VideoPresenter {
    private VideoView mVideoView;
    private VideoModel mVideoModel;

    public VideoPresenter(Activity activity,VideoView videoView) {
        this.mVideoView = videoView;
        mVideoModel = new VideoModelImpl(activity);
    }

    public void getVideoInfo(String url) {
        mVideoModel.getVideoData(url, new OnLoadingListener() {
            @Override
            public void onSuccess(Document document) {

                Elements videos = document.select("div[data-video]");
                Elements images = document.select("[width=300]");
                Elements titles = document.select("strong.js-convert-emoji");
                ArrayList<Video> arrayList = new ArrayList<>(videos.size());

                if (images.size() == videos.size()&& videos.size() == titles.size()) {
                    for (int i=0; i<videos.size(); i++) {
                        Video video = new Video();
                        video.setVideoUrl(videos.get(i).attr("data-video"));
                        video.setImgUrl(images.get(i).attr("src"));
                        video.setTitle(titles.get(i).text());
                        arrayList.add(video);
                    }
                }

                mVideoView.setVideoData(arrayList);

            }

            @Override
            public void onFail() {

            }

            @Override
            public void onEmpty() {

            }

            @Override
            public void onLoading() {

            }
        });
    }
}
