package net.keyfunapp.lib.envswitcher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by huikey on 9/3/16.
 */
public class SwitcherDialogFragment extends DialogFragment {

    public String[] array = {};
    private Context mContext = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        array = bundle.getStringArray("array");
        mContext = getContext();

        String previous = EnvSwitcher.loadPreferences(mContext);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("EnvSwitcher: \nCurrent Selected [" + previous + "]")
                .setItems(array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(which);
                        EnvSwitcher.savePreferences(mContext, array[which]);

                        EnvSwitcher.getInstance().restartApp();
                    }
                });

//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//
//            }
//        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // nth
            }
        });

        return builder.create();
    }
}
