package com.anwesome.ui.pinlockscreen.utils;

import com.anwesome.ui.pinlockscreen.PinAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 04/04/17.
 */
public class AnimationController {
    private List<PinAnimation> pinAnimations = new ArrayList<>();
    private boolean isAnimated = false;
    public void addAnimation(PinAnimation pinAnimation) {
        if(!isAnimated) {
            pinAnimations.add(pinAnimation);
        }
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
            }
        }
    }
    public void start() {
        if(!isAnimated && pinAnimations.size()>0) {
            isAnimated = true;
        }
    }


}
