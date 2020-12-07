package com.example.knock_knock.Component;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.knock_knock.DTO.DeviceInfo;
import com.example.knock_knock.DTO.LogInfo;
import com.example.knock_knock.DTO.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class AppViewModel extends AndroidViewModel {


    private UserInfo userInfo;
    private DeviceInfo curDevice;

    private String macAddress = Const.USER_MAC;

    private MutableLiveData<List<DeviceInfo>> deviceInfoList;
    private MutableLiveData<List<LogInfo>> logInfoList;
    private MutableLiveData<List<DeviceInfo>> outDeviceList;



    public AppViewModel(@NonNull Application application) {
        super(application);
        deviceInfoList = new MutableLiveData<>();
        logInfoList = new MutableLiveData<>();
        outDeviceList = new MutableLiveData<>();

        deviceInfoList.setValue(new ArrayList<>());
        logInfoList.setValue(new ArrayList<>());
        outDeviceList.setValue(new ArrayList<>());
    }

    public AppViewModel() {
        super(null);
    }


    public MutableLiveData<List<DeviceInfo>> observeOutDeviceList() { return outDeviceList; }

    public List<DeviceInfo> getOutDeviceList() {
        return outDeviceList.getValue();
    }

    public void setOutDeviceList(List<DeviceInfo> outDeviceList) {
        this.outDeviceList.postValue(outDeviceList);
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
        this.logInfoList.postValue(logInfoList);
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
        this.deviceInfoList.postValue(deviceInfoList);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
