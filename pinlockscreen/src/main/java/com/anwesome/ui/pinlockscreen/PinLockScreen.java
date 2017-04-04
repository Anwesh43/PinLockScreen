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
    private String pin;
    public PinLockScreen(Activity activity,String pin) {
        this.activity = activity;
        this.pin = pin;
    }
    public void show() {
        if(pinLockScreenView == null) {
            pinLockScreenView = new PinLockScreenView(activity, pin, new OnPinMatchListener() {
                @Override
                public void onPinMatch() {
                    hide();
                }
            });
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
    interface OnPinMatchListener {
        void onPinMatch();
    }
}
