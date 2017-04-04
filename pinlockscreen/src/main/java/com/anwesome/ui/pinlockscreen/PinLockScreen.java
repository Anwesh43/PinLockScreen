package com.anwesome.ui.pinlockscreen;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 04/04/17.
 */
public class PinLockScreen {
    private Activity activity;
    private PinLockScreenView pinLockScreenView;
    public PinLockScreen(Activity activity) {
        this.activity = activity;
    }
    public void show() {
        if(pinLockScreenView == null) {
            pinLockScreenView = new PinLockScreenView(activity);
            activity.addContentView(pinLockScreenView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        if(pinLockScreenView.getVisibility() == View.INVISIBLE) {
            pinLockScreenView.setVisibility(View.VISIBLE);
        }
    }
    public void hide() {
        if(pinLockScreenView!=null && pinLockScreenView.getVisibility() == View.VISIBLE) {
            pinLockScreenView.setVisibility(View.INVISIBLE);
        }
    }
}
