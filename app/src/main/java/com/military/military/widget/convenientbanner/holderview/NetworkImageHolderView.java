package com.military.military.widget.convenientbanner.holderview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.military.R;
import com.military.military.widget.convenientbanner.holder.Holder;
import com.squareup.picasso.Picasso;


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

        View view = LayoutInflater.from(context).inflate(R.layout.layout_banner,null);
        imageView = (ImageView) view.findViewById(R.id.image);
        textView = (TextView) view.findViewById(R.id.title);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, String data,String title) {
//        imageView.setImageResource(R.drawable.icon_default_large);
        if (!TextUtils.isEmpty(title)) {
            textView.setText(title);
        }
        if (!TextUtils.isEmpty(data)) {
            Picasso.with(context).load(data).into(imageView);
        }


    }
}
