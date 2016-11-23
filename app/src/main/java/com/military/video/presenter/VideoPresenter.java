package com.military.video.presenter;

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

    public VideoPresenter(VideoView videoView) {
        this.mVideoView = videoView;
        mVideoModel = new VideoModelImpl();
    }

    public void getVideoInfo(String url) {
        mVideoModel.getVideoData(url, new OnLoadingListener() {
            @Override
            public void onSuccess(Document document) {
                ArrayList<Video> arrayList = new ArrayList<>();
                Elements links = document.select("div.f-video ");
                Log.d("links","links--" + links.size());
                for (Element element:links) {
                    Log.d("element","data-video--" + element.attr("data-video"));
                    Video video = new Video();
                    video.setVideoUrl(element.attr("data-video"));
                    Elements e = element.select("img");
                    for (Element el : e) {
                        Log.d("element","img--" + el.attr("src"));
                        video.setImgUrl(el.attr("src"));
                        video.setTitle(el.attr("alt"));
                        Log.d("element","test--" + el.attr("alt"));
                    }
                    arrayList.add(video);

                }

                for (int i=0; i<arrayList.size(); i++) {
                    Log.d("array","arrayDocu---" + arrayList.get(i).toString());
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
