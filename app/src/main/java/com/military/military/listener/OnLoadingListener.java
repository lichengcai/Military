package com.military.military.listener;

import org.jsoup.nodes.Document;

/**
 * Created by lichengcai on 2016/11/23.
 */

public interface OnLoadingListener {
    void onSuccess(Document json);
    void onFail();
    void onEmpty();
    void onLoading();

}
