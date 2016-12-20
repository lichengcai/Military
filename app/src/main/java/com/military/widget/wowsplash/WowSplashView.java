package com.military.widget.wowsplash;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.military.R;


/**
 * Created by wing on 12/12/16.
 */

public class WowSplashView extends View {
  private float mLength;
  private PathMeasure mTowerPathMeasure;
  private Path mTowerPath;
  private float mAnimatorValue;
  private Path mTowerDst;
  private Paint mPaint;
  private boolean isAnimateEnd;

  public static final float SCALE = 2f;
  public static float translateX;
  public static float translateY;

  // from the svg file
  private int mTowerHeight = 600;
  private int mTowerWidth = 440;


  private long mDuration = 3000;
  private int mWidth;

  private OnEndListener mListener;
  private int mAlpha;

  public WowSplashView(Context context) {
    this(context, null);
  }

  public WowSplashView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public WowSplashView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setBackgroundColor(context.getResources().getColor(R.color.transparent));
    setDrawingCacheEnabled(true);

    if (Build.VERSION.SDK_INT < 21) {
      setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }
    mPaint = new Paint();

    //init TowerPath

    mTowerPath = new SvgPathParser().parsePath(context.getResources().getString(R.string.air));

    mTowerPathMeasure = new PathMeasure(mTowerPath, true);
    mLength = mTowerPathMeasure.getLength();
    mTowerDst = new Path();

  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mWidth = w;
  }


  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mPaint.setStyle(Paint.Style.STROKE);
    //这里SVG过小  就临时这样适配一下。。
    canvas.scale(SCALE, SCALE);
    translateX = (mWidth - mTowerWidth * SCALE) / 2 - 80;
    translateY = 100;
    canvas.translate(translateX, translateY);

    mTowerDst.reset();
    mPaint.setAntiAlias(true);
    mPaint.setStrokeWidth(2);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setColor(getResources().getColor(R.color.black));
    //canvas.drawPath(mTowerPath, paint);

    float stop = mLength * mAnimatorValue;
    mTowerPathMeasure.getSegment(0, stop, mTowerDst, true);

    drawTower(canvas);
    mPaint.setAlpha(255);

  }


  private void drawTower(Canvas canvas) {
    canvas.drawPath(mTowerDst, mPaint);

    if (isAnimateEnd) {
      mPaint.setAlpha(mAlpha);
      canvas.drawPath(mTowerPath, mPaint);
    }
  }



  public void startAnimate() {
    //start tower animate
    getTowerValueAnimator().start();

  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

  }

  @NonNull
  private ValueAnimator getTowerValueAnimator() {
    final ValueAnimator towerAnimator = ValueAnimator.ofFloat(0, 1);
    towerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator valueAnimator) {
        mAnimatorValue = (float) valueAnimator.getAnimatedValue();
        postInvalidateDelayed(10);
      }
    });
    towerAnimator.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animator) {
        isAnimateEnd = false;
      }

      @Override public void onAnimationEnd(Animator animator) {

        isAnimateEnd = true;
        invalidate();

        getAlphaAnimator().start();

        towerAnimator.removeAllUpdateListeners();
      }

      @Override public void onAnimationCancel(Animator animator) {

      }

      @Override public void onAnimationRepeat(Animator animator) {

      }
    });

    towerAnimator.setInterpolator(new DecelerateInterpolator());
    towerAnimator.setDuration(mDuration);
    return towerAnimator;
  }

  private ValueAnimator getAlphaAnimator() {
    final ValueAnimator va = ValueAnimator.ofInt(0, 255);
    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        mAlpha = (int) animation.getAnimatedValue();
        invalidate();
      }
    });
    va.addListener(new Animator.AnimatorListener() {
      @Override public void onAnimationStart(Animator animation) {

      }

      @Override public void onAnimationEnd(Animator animation) {
        va.removeAllUpdateListeners();
        if (mListener != null) {
          mListener.onEnd(WowSplashView.this);
        }
      }

      @Override public void onAnimationCancel(Animator animation) {

      }

      @Override public void onAnimationRepeat(Animator animation) {

      }
    });
    va.setDuration(500);
    return va;
  }

  public interface OnEndListener {
    void onEnd(WowSplashView wowSplashView);
  }

  public void setOnEndListener(OnEndListener listener) {
    mListener = listener;
  }
}
