package com.example.knock_knock.Component;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class BluetoothService {


    private final Runnable mRunnable;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothScanner;
    private MutableLiveData<Boolean> isDoorFound;

    public class BluetoothRunnable implements Runnable {
        @Override
        public void run() {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            mBluetoothScanner = mBluetoothAdapter.getBluetoothLeScanner();

            mBluetoothScanner.startScan(new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    if (result.getDevice().getName() == null) {
                        Log.d("DUMMY", "dym");
                        return ;
                    }

//                    if (result.getDevice().getAddress().equals("B8:27:EB:62:BB:83")) {
//                        Log.d("HELLO","FOUND : " + result.getDevice().getName());
//                        isDoorFound.setValue(true);
//                    }

                    if (result.getDevice().getName().contains("TV")) {
                        Log.d("HELLO","FOUND : " + result.getDevice().getName());
                        isDoorFound.setValue(true);
                    }

                }
            });
        }
    }


    public BluetoothService(MutableLiveData<Boolean> isDoorFound) {
        mRunnable = new BluetoothRunnable();
        this.isDoorFound = isDoorFound;
    }

    public void startScan() {
        Thread thread = new Thread(mRunnable);
        thread.start();
    }


}
