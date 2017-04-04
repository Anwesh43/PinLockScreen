package com.anwesome.ui.pinlockscreen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 04/04/17.
 */
public class PinLockScreenView extends View {
    public PinLockScreenView(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas, Paint paint) {

    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
