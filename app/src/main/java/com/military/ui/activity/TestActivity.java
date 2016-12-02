package com.military.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;


import com.military.R;
import com.snalib.ShareFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by lichengcai on 2016/11/16.
 */

public class TestActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://weapon.huanqiu.com/weaponlist/").get();
                    Log.d("thread"," document --" + document.toString());
                    Log.d("thread"," document --" + document.outerHtml());
                    Document document1 = new Document("");
                    document1.html(document.html());
                    Log.d("thread"," document1 --" + document1.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
