package com.example.sampleandroidanimationsandtransitions;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class AnimatedPathView extends View {

    Paint paint;
    Path path;
    int phase = 0;

    public AnimatedPathView(Context context) {
        super(context);
        init();
    }

    public AnimatedPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimatedPathView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        path = new Path();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float intervals[] = new float[]{30, 15};
        int strokeWidth = 10;

        int width = getWidth();
        int height = getHeight();

        int x1 = 0;
        int y1 = height / 2;
        int x2 = width / 2;
        int y2 = 0;
        int x3 = width;
        int y3 = strokeWidth + 5;

        //Draw a bezier curve
        paint.setColor(0xFF42AE84);
        paint.setStrokeWidth(strokeWidth);
        paint.setPathEffect(new DashPathEffect(intervals, phase));
        phase += 5;
        path.moveTo(strokeWidth + 5, height);
        path.cubicTo(x1, y1, x2, y2, x3, y3);
        canvas.drawPath(path, paint);

        path.reset();

        //paint.setShader(new LinearGradient(getWidth(), getHeight(), getWidth(), getHeight(), Color.GREEN, Color.BLACK, Shader.TileMode.REPEAT));
        //paint.setPathEffect(new CornerPathEffect(250));

        // Draw a straight lined shape
        paint.setColor(0xFFE55B8D);
        path.moveTo(width, 0);
        path.lineTo(width / 2, height / 2);
        path.lineTo(width, height);

        canvas.drawPath(path, paint);

        // Draw a rectangle around the canvas to indicate its borders
        Rect rect = new Rect(0, 0, width, height);
        paint.setColor(Color.WHITE);
        paint.setPathEffect(new PathEffect());
        canvas.drawRect(rect, paint);

        postInvalidateDelayed(50);

    }
}