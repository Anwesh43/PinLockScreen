package com.anwesome.ui.pinlockscreen;

import android.graphics.*;

import com.anwesome.ui.pinlockscreen.utils.DrawingKeyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 04/04/17.
 */
public class PinKeyPad {
    private float x,y,w;
    private String value = "";
    private List<PinKey> pinKeys = new ArrayList<>();
    public PinKeyPad(float x,float y,float w) {
        this.x = x;
        this.y = y;
        this.w = w;
        init();
    }
    public String getValue() {
        return value;
    }
    public void draw(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(x,y);
        for(PinKey pinKey:pinKeys) {
            pinKey.draw(canvas,paint);
        }
        canvas.restore();
    }
    public boolean handleTap(float x,float y) {
        x-=this.x;
        y-=this.y;
        for(PinKey pinKey:pinKeys) {
            return pinKey.handleTap(x,y);
        }
        return false;
    }
    public void init() {
        float keyX = 0,keyY = 0,size = w/3;
        for(int i = 1;i<=9;i++) {
            final String text = ""+i;
            PinKey pinKey = new PinKey(text,keyX, keyY, new KeyDrawer() {
                @Override
                public void drawKey(Canvas canvas, Paint paint, float size,String text) {
                    DrawingKeyUtil.drawKey(canvas,paint,size,text);
                }
            });
            pinKeys.add(pinKey);
            keyX+=size;
            if(i%3 == 0) {
                keyX = 0;
                keyY = keyY+size;
            }
        }
        PinKey pinKey = new PinKey("0", keyX + size, keyY, new KeyDrawer() {
            @Override
            public void drawKey(Canvas canvas, Paint paint, float size,String text) {
                DrawingKeyUtil.drawKey(canvas,paint,size,text);
            }
        });
        PinKey backKey = new PinKey("back", keyX + 2*size, keyY, new KeyDrawer() {
            @Override
            public void drawKey(Canvas canvas, Paint paint, float size,String text) {
                DrawingKeyUtil.drawKey(canvas,paint,size,text);
            }
        });
        pinKeys.add(pinKey);
        pinKeys.add(backKey);
    }
    private class PinKey {
        private float x,y,size;
        private String val;
        private KeyDrawer keyDrawer;
        public PinKey(String val,float x,float y,KeyDrawer keyDrawer) {
            this.x = x;
            this.val = val;
            this.y = y;
            this.size = w/3;
            this.keyDrawer = keyDrawer;
        }
        public void draw(Canvas canvas, Paint paint) {
            canvas.save();
            canvas.translate(x,y);
            keyDrawer.drawKey(canvas,paint,size,val);
            canvas.restore();
        }
        public boolean handleTap(float x,float y) {
            boolean conditon =  x>=this.x && x<=this.x+size && y>=this.y && y<=this.y+size;
            if(conditon) {
                if(val!="back") {
                    value.substring(0,value.length()-1);
                }
                else {
                    value = value+"val";
                }
            }
            return conditon;
        }
        public int hashCode() {
            return (int)(x+y+size)+val.hashCode();
        }
    }
    private interface KeyDrawer {
        void drawKey(Canvas canvas,Paint paint,float size,String text);
    }
}
