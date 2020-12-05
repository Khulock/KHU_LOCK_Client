package com.example.knock_knock.Component;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.knock_knock.DTO.DeviceInfo;
import com.example.knock_knock.DTO.LogInfo;
import com.example.knock_knock.DTO.UserInfo;

import java.util.List;

public class AppViewModel extends AndroidViewModel {


    private UserInfo userInfo;
    private DeviceInfo curDevice;

    private MutableLiveData<List<DeviceInfo>> deviceInfoList;
    private MutableLiveData<List<LogInfo>> logInfoList;




    public AppViewModel(@NonNull Application application) {
        super(application);
        deviceInfoList = new MutableLiveData<List<DeviceInfo>>();
        logInfoList = new MutableLiveData<>();
    }

    public AppViewModel() {
        super(null);
    }



    public MutableLiveData<List<DeviceInfo>> observeDeviceInfoList() {
        return deviceInfoList;
    }

    public MutableLiveData<List<LogInfo>> observeLogInfoList() {
        return logInfoList;
    }

    public List<LogInfo> getLogInfoList() {
        return logInfoList.getValue();
    }

    public void setLogInfoList(List<LogInfo> logInfoList) {
        this.logInfoList.setValue(logInfoList);
    }

    public DeviceInfo getCurDevice() {
        return curDevice;
    }

    public void setCurDevice(DeviceInfo curDevice) {
        this.curDevice = curDevice;
    }

    public List<DeviceInfo> getDeviceInfoList() {
        return deviceInfoList.getValue();
    }

    public void setDeviceInfoList(List<DeviceInfo> deviceInfoList) {
        this.deviceInfoList.setValue(deviceInfoList);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
