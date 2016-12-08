package com.military.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.military.R;
import com.military.utils.Util;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by lichengcai on 2016/11/16.
 */

public class TestActivity extends BaseActivity {
    private IWXAPI wxApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        wxApi = WXAPIFactory.createWXAPI(this, "wx1de1c9f054645e8c");
        wxApi.registerApp("wx1de1c9f054645e8c");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wechatShare(0);
            }
        });

    }


    /**
     *  微信分享方法
     * @param flag
     */
    private void wechatShare(int flag) {
        //判断是否安装微信
        if(wxApi.isWXAppInstalled()) {
            WXWebpageObject webpage = new WXWebpageObject();
            WXMediaMessage msg = new WXMediaMessage(webpage);

                //分享的链接
                webpage.webpageUrl = "http://www.baidu.com";
                //分享标题--课程标题
                msg.title = "test";
                //分享内容--主讲老师
                msg.description = "主讲：" + "Lcc";

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = msg;
            req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
            wxApi.sendReq(req);

            }else {
                Toast.makeText(this,"您还没有安装微信",Toast.LENGTH_SHORT).show();
        }
    }
}
