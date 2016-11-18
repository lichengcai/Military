package com.military.widget.convenientbanner.holderview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.military.R;
import com.military.widget.convenientbanner.holder.Holder;


/**
 * 网络图片加载例子
 */
public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;
    private TextView textView;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
//        imageView = new ImageView(context);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        return imageView;

        View view = LayoutInflater.from(context).inflate(R.layout.activity_test,null);
        imageView = (ImageView) view.findViewById(R.id.image);
        textView = (TextView) view.findViewById(R.id.title);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, String data,String title) {
//        imageView.setImageResource(R.drawable.icon_default_large);
        textView.setText(title);
        Glide.with(context).load(data).into(imageView);

    }
}
