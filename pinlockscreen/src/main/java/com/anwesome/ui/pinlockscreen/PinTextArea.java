package com.anwesome.ui.pinlockscreen;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.anwesome.ui.pinlockscreen.utils.AnimationController;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 04/04/17.
 */
public class PinTextArea {
    private int n=0;
    private float x,y,w;
    private AnimationController animationController;
    private ConcurrentLinkedQueue<PinDot> pinDots = new ConcurrentLinkedQueue<>();
    private PinDot currPinDot;
    public PinTextArea(float x,float y,float w,AnimationController animationController) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.animationController = animationController;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(w/60);
        canvas.drawLine(0,0,w,0,paint);
        for(PinDot pinDot:pinDots) {
            pinDot.draw(canvas,paint);
        }
        canvas.restore();
    }
    public void setN(int n) {
        int dir = 1;
        if(n>this.n) {
            currPinDot = new PinDot(w);
            dir = -1;
            this.animationController.addAnimation(new PinAnimation() {
                @Override
                public void animate() {
                    currPinDot.update();
                }

                @Override
                public boolean stop() {
                    return currPinDot.stop();
                }
            });
            pinDots.add(currPinDot);
        }
        else {
            pinDots.remove(currPinDot);
            currPinDot = pinDots.peek();
        }
        for(PinDot pinDot:pinDots) {
            pinDot.changeX(dir);
        }
        this.n = n;
    }
    private class PinDot {
        private float scale = 0.8f,x,y,r,dir = 1;
        public PinDot(float x) {
            this.x = x+w/8;
            this.r = w/16;
            this.y = -5*r/4;
        }
        public void draw(Canvas canvas,Paint paint) {
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.save();
            canvas.translate(x,y);
            canvas.scale(scale,scale);
            canvas.drawCircle(0,0,r,paint);
            canvas.restore();
        }
        public void changeX(int dir) {
            x+=(w/4)*dir;
        }
        public int hashCode() {
            return (int)(x+y+r);
        }
        public boolean stop() {
            return dir == 0;
        }
        public void update() {
            scale+=dir*0.1f;
            if(scale>=1.2f) {
                dir=-1;
            }
            if(scale<=0.8f) {
                scale = 0.8f;
                dir = 0;
            }
        }
    }
}
