package net.keyfunapp.lib.envswitcher;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;

/**
 * Created by key_hui on 9/3/2016.
 */
public class EnvSwitcher {
    private static final String PREFERENCES_NAME = "EnvSwitcher";
    private static final String KEY_ENVIRONMENT = "environment";

    private static EnvSwitcher ourInstance = new EnvSwitcher();

    public static EnvSwitcher getInstance() {
        return ourInstance;
    }

    SimpleTwoFingerLongPressDetector multiTouchListener;

    private FragmentActivity mContext = null;
    private String[] mArray = {};
    private Class<?> mClass = null;

    private EnvSwitcher() {
        multiTouchListener = new SimpleTwoFingerLongPressDetector() {
            @Override
            public void onTwoFingerLongPress() {
                SwitcherDialogFragment newFragment = new SwitcherDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putStringArray("array", mArray);
                newFragment.setArguments(bundle);
                newFragment.show(mContext.getSupportFragmentManager(), "SwitcherDialogFragment");
            }
        };
    }

    public void init(FragmentActivity context, String[] array, Class<?> cls) {
        this.mContext = context;
        this.mArray = array;
        this.mClass = cls;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(multiTouchListener.onTouchEvent(event)) {
            return true;
        }
        return false;
    }

    public String loadEnvironment(Context context) {
        return loadPreferences(context);
    }

    public static String loadPreferences(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        String value = pref.getString(KEY_ENVIRONMENT, "");
        return value;
    }

    public static void savePreferences(Context context, String value) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_APPEND);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_ENVIRONMENT, value);
        editor.commit();
    }

    public void restartApp() {
        if(mClass != null) {
            Intent mStartActivity = new Intent(mContext, mClass);
            int mPendingIntentId = 81777778;
            PendingIntent mPendingIntent = PendingIntent.getActivity(mContext, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);

            if (mContext != null) {
                mContext.moveTaskToBack(true);
            }
        }
        System.exit(0);
    }
}
