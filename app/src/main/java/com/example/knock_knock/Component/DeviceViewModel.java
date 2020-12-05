package com.example.knock_knock.Component;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.knock_knock.DTO.DeviceInfo;
import com.example.knock_knock.DTO.UserInfo;

import java.util.List;

public class DeviceViewModel extends AndroidViewModel {

    private List<DeviceInfo> deviceInfoList;
    private UserInfo userInfo;
    private DeviceInfo curDevice;

    public DeviceInfo getCurDevice() {
        return curDevice;
    }

    public void setCurDevice(DeviceInfo curDevice) {
        this.curDevice = curDevice;
    }

    public DeviceViewModel(@NonNull Application application) {
        super(application);
    }
    public DeviceViewModel() {
        super(null);
    }




    public List<DeviceInfo> getDeviceInfoList() {
        return deviceInfoList;
    }

    public void setDeviceInfoList(List<DeviceInfo> deviceInfoList) {
        this.deviceInfoList = deviceInfoList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
