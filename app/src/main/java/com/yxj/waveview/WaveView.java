package com.yxj.waveview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
    private int mWidth, mHeight;
    private int waveHeight = 200;
    private int waveStep;
    private int startOffset = 100;
    private int color;
    private int horizontalLine;   // 水平线，view底部到水平线的高度

    private boolean direction;   // 向右：false，向左:true
    private ValueAnimator animator;

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
        color = getResources().getColor(R.color.color_blue);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        waveStep = mWidth / 2;
        horizontalLine = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.clipRect(0, 0, mWidth, mHeight);
        int resultHorizontal = mHeight - horizontalLine;

        if (direction) {
            Matrix matrix = new Matrix();
            float[] matrixValues = new float[]{
                    -1, 0, 2 * waveStep,
                    0, 1, 0,
                    0, 0, 1
            };
            matrix.setValues(matrixValues);
            canvas.setMatrix(matrix);
        }

        offset = (int) (fraction * waveStep) + startOffset;

        canvas.save();
        canvas.translate(0, resultHorizontal);

        Path path = new Path();
        // 右下角的点
        path.moveTo(offset + waveStep * -3, mHeight - resultHorizontal);
        path.rLineTo(0, -(mHeight - resultHorizontal));
        for (int i = -2; i <= 2; i++) {
            int x1 = offset + waveStep / 2 + waveStep * (i - 1);
            int y1 = getWaveHeight(i);

            int x2 = offset + waveStep * i;
            int y2 = 0;

            path.quadTo(x1, y1, x2, y2);
        }
        path.rLineTo(0, mHeight - resultHorizontal);
        path.close();

        canvas.drawPath(path, paint);
        canvas.restore();
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

    private void start() {
        if (animator != null) {
            animator.cancel();
        }

        animator = ValueAnimator.ofFloat(0, 2f);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction = (Float) animation.getAnimatedValue();
                invalidate(mWidth/2,0,mWidth,mHeight);
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

    public WaveView setDirection(boolean direction) {
        this.direction = direction;
        return this;
    }

    public WaveView setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public WaveView setWaveHeight(int waveHeight) {
        this.waveHeight = waveHeight;
        return this;
    }

    public WaveView setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public WaveView setColor(int color) {
        this.color = color;
        return this;
    }

    public WaveView setHorizontalLine(int horizontalLine) {
        this.horizontalLine = horizontalLine;
        return this;
    }

    public void build() {
        invalidate();
    }

    public void move() {
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator != null) {
            animator.cancel();
        }
    }
}
