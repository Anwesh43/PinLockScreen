package com.anwesome.ui.pinlockscreen;

import android.content.Context;
import android.graphics.*;
import android.view.*;

/**
 * Created by anweshmishra on 04/04/17.
 */
public class PinLockScreenView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private PinKeyPad pinKeyPad;
    private int w,h;
    private int time = 0;
    public PinLockScreenView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            pinKeyPad = new PinKeyPad(w/4,h/2,3*w/4);
        }
        canvas.drawColor(Color.parseColor("#AAFFFFFF"));
        pinKeyPad.draw(canvas,paint);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
