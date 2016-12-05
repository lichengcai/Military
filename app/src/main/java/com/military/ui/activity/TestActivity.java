package com.military.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.military.R;
import com.military.widget.customdialog.BottomDialog;
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

//        Document document = (Document) msg.obj;
//        Elements elements = document.select("div.intron");
//        Log.d("elements","elements--" + elements);
//        Elements elements1 = document.select("div.otherList");
//        for (Element element : elements1) {
//            Log.d("elements","name--" + element.select("h3.title_").get(0).text());
//        }
//        Log.d("elements","elements1==" + elements1.size() + "\n " + elements1.get(0).text());

    }

    public void test(View view) {

    }
}
