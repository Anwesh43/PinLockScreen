package com.anwesome.ui.pinlockscreen;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
        if(activity instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity)activity).getSupportActionBar();
            if(actionBar!=null) {
                actionBar.hide();
            }
        }
        else {
            android.app.ActionBar actionBar = activity.getActionBar();
            if(actionBar!=null) {
                actionBar.hide();
            }
        }
    }
    public void hide() {
        if(pinLockScreenView!=null && pinLockScreenView.getVisibility() == View.VISIBLE) {
            pinLockScreenView.setVisibility(View.INVISIBLE);
            if(activity instanceof AppCompatActivity) {
                ActionBar actionBar = ((AppCompatActivity)activity).getSupportActionBar();
                if(actionBar!=null) {
                    actionBar.show();
                }
            }
            else {
                android.app.ActionBar actionBar = activity.getActionBar();
                if(actionBar!=null) {
                    actionBar.show();
                }
            }
        }
    }
    interface OnPinMatchListener {
        void onPinMatch();
    }
}
