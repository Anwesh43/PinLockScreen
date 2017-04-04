package com.anwesome.ui.pinlockscreen.utils;

import com.anwesome.ui.pinlockscreen.PinAnimation;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 04/04/17.
 */
public class AnimationController {
    private ConcurrentLinkedQueue<PinAnimation> pinAnimations = new ConcurrentLinkedQueue<>();
    private boolean isAnimated = false;
    private OnAnimationStopListener onAnimationStopListener;
    public void addAnimation(PinAnimation pinAnimation) {
        if(!isAnimated) {
            pinAnimations.add(pinAnimation);
        }
    }
    public void setOnAnimationStopListener(OnAnimationStopListener onAnimationStopListener) {
        this.onAnimationStopListener = onAnimationStopListener;
    }
    public boolean animating() {
        return isAnimated;
    }
    public void animate() {
        if(isAnimated) {
            for (PinAnimation pinAnimation : pinAnimations) {
                pinAnimation.animate();
                if (pinAnimation.stop()) {
                    pinAnimations.remove(pinAnimation);
                }
            }
            if(pinAnimations.size() == 0) {
                isAnimated = false;
                if(onAnimationStopListener != null) {
                    onAnimationStopListener.onAnimationStop();
                }
            }
        }
    }
    public void start() {
        if(!isAnimated && pinAnimations.size()>0) {
            isAnimated = true;
        }
    }
    public interface OnAnimationStopListener {
        void onAnimationStop();
    }

}
