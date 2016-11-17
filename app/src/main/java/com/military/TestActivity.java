package com.military;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by lichengcai on 2016/11/16.
 */

public class TestActivity extends BaseActivity {
    TextView textView;
    private Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    StringBuilder stringBuilder = new StringBuilder();
                    Document document = (Document) msg.obj;
                    Elements links = document.select("div.newsFir");
                    Log.d("links"," links---" + links.size());
                    for (Element element : links) {
                        Elements elements = element.select("a");
                        for (Element element1 : elements) {
                            Log.d("title","title--" + element1.attr("title"));
                            stringBuilder.append(element1.attr("title")).append("\n");
                        }

                    }
                    textView.setText(stringBuilder.toString());
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = (TextView) findViewById(R.id.text);

        getData();
    }

    public void getData() {
        //solo
        new Thread(new Runnable() {
            @Override
            public void run() {
                //http://movie.douban.com/review/best/?start=0
                String url = "http://mil.huanqiu.com/";
                try {
                    Document document = Jsoup.connect(url).get();
                    handler.obtainMessage(1,document).sendToTarget();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
