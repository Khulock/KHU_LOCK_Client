package com.example.knock_knock.DTO;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    private String name;
    private int id;
    private String token;

    private List<DeviceInfo> outDevice;

    public UserInfo(String name, int id) {
        this.name = name;
        this.id = id;
        outDevice = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
