package com.military.huanqiu.weapon;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.military.MilitaryApplication;
import com.military.R;
import com.military.ui.activity.BaseActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeaponDetailActivity extends BaseActivity {
    @BindView(R.id.text)
    TextView textView;

    private MyHandler mHandler  = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<WeaponDetailActivity> ref;
        private WeaponDetailActivity act;

        public MyHandler(WeaponDetailActivity activity) {
            ref = new WeakReference<>(activity);
            act = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Document document = (Document) msg.obj;
                    Elements elements = document.select("div.intron");
                    Log.d("elements","elements--" + elements);
                    Elements elements1 = document.select("div.otherList");
                    for (Element element : elements1) {
                        Log.d("elements","name--" + element.select("h3.title_").get(0).text());
                    }
                    Log.d("elements","elements1==" + elements1.size() + "\n " + elements1.get(0).text());

                    act.textView.setText(elements.get(0).text());
                    break;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_detail);
        ButterKnife.bind(this);

        MilitaryApplication.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://weapon.huanqiu.com/j_16").get();
                    mHandler.obtainMessage(0,document).sendToTarget();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
