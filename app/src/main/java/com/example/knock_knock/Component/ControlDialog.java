package com.example.knock_knock.Component;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ControlDialog {

    private final MaterialAlertDialogBuilder mDialogBuilder;
    private Context mContext;
    private ControlDialogInterface dialogInterface;
    private int checked = 1;



    public ControlDialog(Context context, ControlDialogInterface dialogInterface) {
        mContext = context;
        mDialogBuilder = new MaterialAlertDialogBuilder(mContext);
        this.dialogInterface = dialogInterface;
    }



    public void show(String tag, List<String> controlList, View root) {
        CharSequence[] mDeviceList = controlList.toArray(new CharSequence[controlList.size()]);
        show(tag, mDeviceList, root);
    }

    public void show(String tag, CharSequence[] controlList, View root) {
        mDialogBuilder.setTitle("Level을 설정해주세요")
                .setSingleChoiceItems(controlList, -1, ((dialog, which) -> {
                    checked = which;
                }))
                .setPositiveButton("Confirm", (dialog, which) -> {
                    Snackbar.make(root, "Change level to " + controlList[checked], Snackbar.LENGTH_LONG)
                            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                            .show();
                    dialogInterface.callbackControlDialog(tag, checked);
                }).show();
    }


}