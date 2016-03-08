package net.keyfunapp.EnvSwitcher;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat mDetector;
    SimpleTwoFingerDoubleTapDetector multiTouchListener;
    SimpleTwoFingerLongPressDetector multiTouchListener2;

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        setupGesture();
    }

    void setupGesture() {
        //mDetector = new GestureDetectorCompat(this, new MyGestureListener());

//        multiTouchListener = new SimpleTwoFingerDoubleTapDetector() {
//            @Override
//            public void onTwoFingerDoubleTap() {
//                Toast.makeText(MainActivity.this, "Two Finger Double Tap", Toast.LENGTH_SHORT).show();
//            }
//        };

        multiTouchListener2 = new SimpleTwoFingerLongPressDetector() {
            @Override
            public void onTwoFingerLongPress() {
                Toast.makeText(MainActivity.this, "Two Finger Long Press", Toast.LENGTH_SHORT).show();
                showDialog();
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //this.mDetector.onTouchEvent(event);

        if(multiTouchListener2.onTouchEvent(event)) {
            return true;
        }

//        if(multiTouchListener.onTouchEvent(event)) {
//            return true;
//        }

        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(DEBUG_TAG, "onDown: " + e.toString());
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d(DEBUG_TAG, "onLongPress: " + e.toString());
        }

    }

    void showDialog() {
        DialogFragment newFragment = new CustomDialogFragment();
        newFragment.show(getSupportFragmentManager(), "CustomDialogFragment");
    }
}
