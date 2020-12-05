package com.example.knock_knock.Component;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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

    public void show(List<String> controlList) {
        CharSequence[] mDeviceList = controlList.toArray(new CharSequence[controlList.size()]);
        show(mDeviceList);
    }

    public void show(CharSequence[] controlList) {
        mDialogBuilder.setTitle("Control panel")
                .setSingleChoiceItems(controlList, 1, ((dialog, which) -> {
                    checked = which;
                }))
                .setPositiveButton("Confirm", (dialog, which) -> {
                    Toast.makeText(mContext, "Change level to " + controlList[checked], Toast.LENGTH_LONG).show();
                    dialogInterface.changeLevel(checked);
                }).show();
    }


}