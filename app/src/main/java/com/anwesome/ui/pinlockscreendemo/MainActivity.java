package com.anwesome.ui.pinlockscreendemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.pinlockscreen.PinLockScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PinLockScreen pinLockScreen = new PinLockScreen(this);
        pinLockScreen.show();
    }
}
