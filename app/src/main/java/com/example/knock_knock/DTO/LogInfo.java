package com.example.knock_knock.DTO;

public class LogInfo {

    private String time;
    private String inout;

    public LogInfo(String time, int inout) {
        this.time = time;
        this.inout = inout == 0 ? "OUT" : "IN";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInout() {
        return inout;
    }

    public void setInout(String inout) {
        this.inout = inout;
    }
}
