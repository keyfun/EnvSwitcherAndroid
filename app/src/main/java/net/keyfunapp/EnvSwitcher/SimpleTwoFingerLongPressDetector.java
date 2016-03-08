package net.keyfunapp.EnvSwitcher;

import android.view.MotionEvent;

/**
 * Created by huikey on 9/3/16.
 */
public abstract class SimpleTwoFingerLongPressDetector {
    private static final int TIMEOUT = 1000;
    private long mSecondFingerDownTime = 0;

    private void reset() {
        mSecondFingerDownTime = 0;
    }

    public boolean onTouchEvent(MotionEvent event) {

//        System.out.println(event.getActionIndex());
//        System.out.println(event.getPointerCount());

        if(event.getActionIndex() == 0) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    mSecondFingerDownTime = System.currentTimeMillis();
//                    System.out.println("ACTION_DOWN");
                    break;

                case MotionEvent.ACTION_POINTER_UP:
//                    System.out.println("ACTION_POINTER_UP");
                    if ((System.currentTimeMillis() - mSecondFingerDownTime) >= TIMEOUT) {
                        //long double-press action
//                        System.out.println("long double-press action");
                        onTwoFingerLongPress();
                        return true;
                    } else {
                        //short double-press action
//                        System.out.println("short double-press action");
                        return false;
                    }
            }
        }
        return false;
    }

    public abstract void onTwoFingerLongPress();
}
