package net.keyfunapp.EnvSwitcher;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import net.keyfunapp.lib.envswitcher.EnvSwitcher;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        setupGesture();
    }

    void setupGesture() {
        String[] list = {"Development", "Production", "Key Test"};
        EnvSwitcher.getInstance().init(this, list, MainActivity.class);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(EnvSwitcher.getInstance().onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }
}
