package com.anwesome.ui.pinlockscreen.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by anweshmishra on 04/04/17.
 */
public class DrawingKeyUtil {
    public static void drawKey(Canvas canvas, Paint paint,float size,String text) {
        if(text.equals("back")) {
            drawBackSpace(canvas,paint,size,text);
        }
        else {
            drawNumber(canvas,paint,size,text);
        }
    }
    private static void drawNumber(Canvas canvas, Paint paint,float size,String text) {
        paint.setColor(Color.WHITE);
        paint.setTextSize(size/3);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text,size/2,size/2,paint);
    }
    private static void drawBackSpace(Canvas canvas, Paint paint,float size,String text) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.save();
        canvas.translate(size/2,size/2);
        Path path = new Path();
        path.moveTo(-size/2,0);
        path.lineTo(0,-size/2);
        path.lineTo(0,size/2);
        path.lineTo(size/2,size/2);
        path.lineTo(0,size/2);
        path.lineTo(-size/2,0);
        canvas.drawPath(path,paint);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(size/20);
        for(int i=0;i<2;i++) {
            canvas.save();
            canvas.rotate(90*i+45);
            canvas.drawLine(0,-size/2,0,size/2,paint);
            canvas.restore();
        }
        canvas.restore();
    }
}
