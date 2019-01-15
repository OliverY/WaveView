package com.yxj.waveview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;


/**
 * Author:  Yxj
 * Time:    2019/1/14 下午1:43
 * -----------------------------------------
 * Description:
 */
public class WaveView extends View {

    private Paint paint;
    private Paint pointPaint;
    private int mWidth, mHeight;
    private int waveHeight = 200;
    private int waveStep;
    private int startOffset = 100;


    private int direction = 1;   // 正向:1，反向:-1

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.color_gray));
        paint.setStrokeWidth(10);

        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        waveStep = mWidth / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        offset = (int) (fraction * waveStep) + startOffset;

        canvas.save();
        canvas.translate(0, mHeight / 2);

        Path path = new Path();

        // 右下角的点
        path.moveTo(offset + waveStep * -3, mHeight / 2);
        path.rLineTo(0, -mHeight / 2);
        Log.e("yxj", "================");

        for (int i = -2; i <= 2; i++) {
            int x1 = offset + waveStep / 2 + waveStep * (i - 1);
            int y1 = getWaveHeight(i);

            int x2 = offset + waveStep * i;
            int y2 = 0;

            path.quadTo(x1, y1, x2, y2);
        }

        path.rLineTo(0, mHeight / 2);
        path.close();

    }

    private int getWaveHeight(int i) {
        if (Math.abs(i % 2) == 1) {
            return waveHeight * -1;
        } else if (Math.abs(i % 2) == 0) {
            return waveHeight;
        }
        return 0;
    }

    private int offset;
    private int duration = 2000;
    private float fraction = 0;

    public void start() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 2f);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

}
