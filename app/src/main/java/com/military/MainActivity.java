package com.military;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends Activity {
    TextView textView;
    private Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    StringBuilder stringBuilder = new StringBuilder();

                    Document document = (Document) msg.obj;

                    Elements links = document.select("a[href]");
                    for (Element link : links) {
                        String linkHref = link.attr("href");
                        stringBuilder.append(linkHref).append("\n");
                        Log.d("link"," linkHref--" + linkHref);
                    }
                    textView.setText(stringBuilder.toString());
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
