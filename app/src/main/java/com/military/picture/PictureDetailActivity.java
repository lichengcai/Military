package com.military.picture;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.FloatMath;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.military.R;
import com.military.bean.Picture;
import com.military.bean.Video;
import com.military.picture.adapter.PicDetailAdapter;
import com.military.picture.presenter.PicturePresenter;
import com.military.picture.view.PictureView;
import com.military.ui.activity.BaseActivity;
import com.military.widget.convenientbanner.ConvenientBanner;
import com.military.widget.convenientbanner.holder.CBViewHolderCreator;
import com.military.widget.convenientbanner.holderview.NetworkImageHolderView;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureDetailActivity extends BaseActivity implements PictureView {
    @BindView(R.id.viewPager_pic)
    ViewPager mViewPager;

    private PicturePresenter mPresenter;
    private PicDetailAdapter mAdapter;
    private static final int MSG_GET_PIC_DETAIL_SUCCESSFUL = 0;

    private PictureDetailHandler mHandler = new PictureDetailHandler(this);

    private static class PictureDetailHandler extends Handler {
        private WeakReference<PictureDetailActivity> ref;
        private PictureDetailActivity act;

        PictureDetailHandler(PictureDetailActivity activity) {
            ref = new WeakReference<>(activity);
            act = ref.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_GET_PIC_DETAIL_SUCCESSFUL:
                    ArrayList<Picture> array = (ArrayList<Picture>) msg.obj;
                    ArrayList<View> arrayView = new ArrayList<>();

                    for (int i=0; i<array.size(); i++) {
//                        View view = LayoutInflater.from(act).inflate(R.layout.layout_test,null);
//                        ImageView image = (ImageView) view.findViewById(R.id.imageView);
                        ImageView image = new ImageView(act);
                        Picasso.with(act).load(array.get(i).getImg_url()).into(image);
                        arrayView.add(image);
                    }
                    act.mAdapter = new PicDetailAdapter(arrayView);
                    act.mViewPager.setAdapter(act.mAdapter);

                    break;
            }
        }
    }

    private static final class TouchListener implements View.OnTouchListener {

        ImageView imageView;
        public TouchListener(ImageView imageView) {
            this.imageView = imageView;
        }
        /** 记录是拖拉照片模式还是放大缩小照片模式 */
        private int mode = 0;// 初始状态
        /** 拖拉照片模式 */
        private static final int MODE_DRAG = 1;
        /** 放大缩小照片模式 */
        private static final int MODE_ZOOM = 2;

        /** 用于记录开始时候的坐标位置 */
        private PointF startPoint = new PointF();
        /** 用于记录拖拉图片移动的坐标位置 */
        private Matrix matrix = new Matrix();
        /** 用于记录图片要进行拖拉时候的坐标位置 */
        private Matrix currentMatrix = new Matrix();

        /** 两个手指的开始距离 */
        private float startDis;
        /** 两个手指的中间点 */
        private PointF midPoint;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            /** 通过与运算保留最后八位 MotionEvent.ACTION_MASK = 255 */
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                // 手指压下屏幕
                case MotionEvent.ACTION_DOWN:
                    mode = MODE_DRAG;
                    // 记录ImageView当前的移动位置
                    currentMatrix.set(imageView.getImageMatrix());
                    startPoint.set(event.getX(), event.getY());
                    break;
                // 手指在屏幕上移动，改事件会被不断触发
                case MotionEvent.ACTION_MOVE:
                    // 拖拉图片
                    if (mode == MODE_DRAG) {
                        float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
                        float dy = event.getY() - startPoint.y; // 得到x轴的移动距离
                        // 在没有移动之前的位置上进行移动
                        matrix.set(currentMatrix);
                        matrix.postTranslate(dx, dy);
                    }
                    // 放大缩小图片
                    else if (mode == MODE_ZOOM) {
                        float endDis = distance(event);// 结束距离
                        if (endDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                            float scale = endDis / startDis;// 得到缩放倍数
                            matrix.set(currentMatrix);
                            matrix.postScale(scale, scale,midPoint.x,midPoint.y);
                        }
                    }
                    break;
                // 手指离开屏幕
                case MotionEvent.ACTION_UP:
                    // 当触点离开屏幕，但是屏幕上还有触点(手指)
                case MotionEvent.ACTION_POINTER_UP:
                    mode = 0;
                    break;
                // 当屏幕上已经有触点(手指)，再有一个触点压下屏幕
                case MotionEvent.ACTION_POINTER_DOWN:
                    mode = MODE_ZOOM;
                    /** 计算两个手指间的距离 */
                    startDis = distance(event);
                    /** 计算两个手指间的中间点 */
                    if (startDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                        midPoint = mid(event);
                        //记录当前ImageView的缩放倍数
                        currentMatrix.set(imageView.getImageMatrix());
                    }
                    break;
            }
            imageView.setImageMatrix(matrix);
            return true;
        }

        /** 计算两个手指间的距离 */
        private float distance(MotionEvent event) {
            float dx = event.getX(1) - event.getX(0);
            float dy = event.getY(1) - event.getY(0);
            /** 使用勾股定理返回两点之间的距离 */
            return (float) Math.sqrt(dx * dx + dy * dy);
        }

        /** 计算两个手指间的中间点 */
        private PointF mid(MotionEvent event) {
            float midX = (event.getX(1) + event.getX(0)) / 2;
            float midY = (event.getY(1) + event.getY(0)) / 2;
            return new PointF(midX, midY);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        ButterKnife.bind(this);

        mPresenter = new PicturePresenter(this,this);


        Video video = (Video) getIntent().getSerializableExtra("Video");
        if (video == null) {
            Toast.makeText(this,"获取图片失败，请重试",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            mPresenter.getPictureDetail(video.getVideoUrl());
        }
    }

    @Override
    public void setPicture(ArrayList<Video> arrayList, boolean loadMore) {

    }

    @Override
    public void setPicDetail(ArrayList<Picture> arrayList) {
        if (mHandler != null){
            if (arrayList.size() != 0) {
                mHandler.obtainMessage(MSG_GET_PIC_DETAIL_SUCCESSFUL,arrayList).sendToTarget();
            }
        }
    }
}
