package com.military.military.transforms;

import android.support.v4.view.ViewPager;

/**
 * Created by lichengcai on 2016/12/6.
 */

public  class TransformerItem {

    public final String title;
    public final Class<? extends ViewPager.PageTransformer> clazz;

    public TransformerItem(Class<? extends ViewPager.PageTransformer> clazz) {
        this.clazz = clazz;
        title = clazz.getSimpleName();
    }

    @Override
    public String toString() {
        return title;
    }

}
