package com.military.video;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.military.MilitaryApplication;
import com.military.R;
import com.military.bean.Video;
import com.military.ui.fragment.FragmentBase;
import com.military.video.presenter.VideoPresenter;
import com.military.video.view.VideoView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/22.
 */

public class FragmentVideo extends FragmentBase implements VideoView{
    @BindView(R.id.text)
    TextView text;
    private int mType = -1;
    private VideoPresenter mPresenter;
    private static final int MSG_GET_DATA_SUCCESS = 0;


    private VideoHandler mHandler = new VideoHandler(this);

    @Override
    public void setVideoData(ArrayList<Video> arrayList) {

    }


    private static class VideoHandler extends Handler {
        private WeakReference<FragmentVideo> ref;
        private FragmentVideo frg;

        VideoHandler(FragmentVideo fragmentVideo) {
            ref = new WeakReference<>(fragmentVideo);
            frg = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_DATA_SUCCESS:
                    Document document = (Document) msg.obj;
                    frg.text.setText(document.toString());
//                    frg.getVideoUrl(document);
                    break;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
        mPresenter = new VideoPresenter(this);
        mPresenter.getVideoInfo("http://www.meipai.com/square/19");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
    }


    public static FragmentVideo newInstance(int type) {
        FragmentVideo fragmentListNews = new FragmentVideo();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        fragmentListNews.setArguments(bundle);
        return fragmentListNews;
    }

    private void getData() {
        MilitaryApplication.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://www.meipai.com/square/19").get();
//                    Log.d("getVideoUrl"," size--" + document);
                    mHandler.obtainMessage(MSG_GET_DATA_SUCCESS,document).sendToTarget();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getVideoUrl(Document document) {
        Elements links = document.select("div.f-video");
        for (Element element:links) {
            Elements e = element.select("img");
            for (Element el : e) {
//                Log.d("element","img--" + el.attr("src"));
//                Log.d("element","test--" + el.attr("alt"));
            }
//            Log.d("element","data-video--" + element.attr("data-video"));
        }
    }
}
