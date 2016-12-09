package com.military.ui.activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;


import com.bm.library.PhotoView;
import com.military.R;
import com.squareup.picasso.Picasso;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lichengcai on 2016/11/16.
 */

public class TestActivity extends BaseActivity {
    private IWXAPI wxApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        ButterKnife.bind(this);

//        Picasso.with(this).load("http://himg2.huanqiu.com/attachment2010/2016/1207/09/35/20161207093512548.jpg").into(photoView);
        // 启用图片缩放功能



    }


    /**
     *  微信分享方法
     * @param flag
     */
    private void wechatShare(int flag) {
        wxApi = WXAPIFactory.createWXAPI(this, "wx1de1c9f054645e8c");
        wxApi.registerApp("wx1de1c9f054645e8c");

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
