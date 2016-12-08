package com.military.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.military.ui.activity.BaseActivity;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


/**
 * Created by lichengcai on 2015/10/29.
 */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    protected IWXAPI mApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApi = WXAPIFactory.createWXAPI(this, "wx1de1c9f054645e8c");

        mApi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mApi.handleIntent(intent, this);
    }
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {

        SendMessageToWX res;
        if (resp instanceof SendMessageToWX.Resp) {
            finish();
            return;
        }
        String code = null;
        Log.d("onResp","onResp resp errCode:--" + resp.errCode);
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                code = sendResp.code;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                break;
            default:
                break;
        }
        finish();
    }

}
