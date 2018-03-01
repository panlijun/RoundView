package com.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * 圆角ImageView，支持圆角外边框
 *
 * @author plj
 */
public class RoundImageView extends ImageView {
    private final RectF roundRect = new RectF();
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();
    private final Paint linePaint = new Paint();
    private float radius = -1;
    private int lineColor;
    private float lineWidth;

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadAttrs(context, attrs);
        init();
    }

    public RoundImageView(Context context) {
        super(context);
        init();
    }

    private void loadAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        radius = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_round_radius, -1);
        lineColor = typedArray.getColor(R.styleable.RoundImageView_round_stoker_color, Color.TRANSPARENT);
        lineWidth = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_round_stoker_width, 0);

        typedArray.recycle();
    }

    private void init() {
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        zonePaint.setAntiAlias(true);
        linePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);
        zonePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        zonePaint.setStrokeWidth(0);

        if (lineWidth > 0) {
            linePaint.setStyle(Paint.Style.STROKE);
            linePaint.setStrokeWidth(lineWidth);
            linePaint.setColor(lineColor);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        // 默认是两边为半圆形，如果不需要圆角radius主动设置为0
        if (radius < 0) {
            radius = w < h ? w : h;
            radius = radius / 2;
        }
        roundRect.set(0, 0, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(roundRect, radius, radius, zonePaint);
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        if (lineWidth > 0) {
            canvas.drawRoundRect(roundRect, radius, radius, linePaint);
        }
        canvas.restore();
    }


}
