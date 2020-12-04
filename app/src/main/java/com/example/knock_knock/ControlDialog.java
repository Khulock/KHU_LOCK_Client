package com.example.knock_knock;

import android.content.Context;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class ControlDialog {

    private final MaterialAlertDialogBuilder mDialogBuilder;
    private Context mContext;
    private int checked = 1;



    public ControlDialog(Context context) {
        mContext = context;
        mDialogBuilder = new MaterialAlertDialogBuilder(mContext);
    }

    public void show(List<String> deviceList) {
        CharSequence[] mDeviceList = deviceList.toArray(new CharSequence[deviceList.size()]);
        mDialogBuilder.setTitle("Control panel")
                .setSingleChoiceItems(mDeviceList, 1, ((dialog, which) -> {
                    checked = which;
                }))
                .setPositiveButton("Confirm", (dialog, which) -> {
                    Toast.makeText(mContext, mDeviceList[checked], Toast.LENGTH_LONG).show();
                }).show();
    }
}