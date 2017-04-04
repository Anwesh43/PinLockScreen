package com.anwesome.ui.pinlockscreen;

import android.content.Context;
import android.graphics.*;
import android.view.*;

import com.anwesome.ui.pinlockscreen.utils.AnimationController;

/**
 * Created by anweshmishra on 04/04/17.
 */
public class PinLockScreenView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private PinKeyPad pinKeyPad;
    private PinTextArea pinTextArea;
    private AnimationController animationController = new AnimationController();
    private int w,h;
    private int time = 0;
    public PinLockScreenView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            pinTextArea = new PinTextArea(w/2-Math.min(w,h)/8,h/6,Math.min(w,h)/4,animationController);
            pinKeyPad = new PinKeyPad(w/4-w/9,h/4,3*w/4,animationController);
        }
        canvas.drawColor(Color.parseColor("#AA000000"));
        pinKeyPad.draw(canvas,paint);
        pinTextArea.draw(canvas,paint);
        time++;
        if(animationController.animating()) {
            animationController.animate();
            try {
                Thread.sleep(50);
                invalidate();

            } catch (Exception ex) {


            }
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(),y = event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN && !animationController.animating()) {
            if(pinKeyPad.handleTap(x,y)) {
                if(pinKeyPad.getValue().length() <= 4) {
                    pinTextArea.setN(pinKeyPad.getValue().length());
                    animationController.start();
                    postInvalidate();
                }
            }
        }
        return true;
    }
}
