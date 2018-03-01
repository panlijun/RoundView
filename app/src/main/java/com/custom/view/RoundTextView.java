package com.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 带圆角且支持{@link android.animation.ValueAnimator}动画效果的textView
 * 支持圆角边框
 *
 * @author plj
 */
public class RoundTextView extends TextView {

    private final Paint paint = new Paint();
    private final RectF roundRect = new RectF();
    private float radius;
    private float lineWidth;
    private int lineColor;
    private int roundColor;


    public RoundTextView(Context context) {
        super(context);
    }

    public RoundTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        loadAttrs(context, attrs);
    }

    private void loadAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundTextView);
        radius = typedArray.getDimensionPixelSize(R.styleable.RoundTextView_round_radius, 0);
        lineColor = typedArray.getColor(R.styleable.RoundTextView_round_stoker_color, Color.TRANSPARENT);
        lineWidth = typedArray.getDimensionPixelSize(R.styleable.RoundTextView_round_stoker_width, 0);
        roundColor = typedArray.getColor(R.styleable.RoundTextView_round_background, Color.TRANSPARENT);

        typedArray.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        roundRect.set(0, 0, w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(roundColor);
        paint.setStrokeWidth(0);
        canvas.drawRoundRect(roundRect, radius, radius, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(lineColor);
        paint.setStrokeWidth(lineWidth);
        canvas.drawRoundRect(roundRect, radius, radius, paint);
        super.onDraw(canvas);
    }


    @Keep
    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
        invalidate();
    }

    @Keep
    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        invalidate();
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }
}