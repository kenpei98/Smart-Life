package com.example.smartsleep;

public class Alarm {
    public String time;
    public int code;
    public int hour;
    public int minute;
    public String type;

    public Alarm(String mTime, int mCode, int h,int m, String ty){
        time = mTime;
        code = mCode;
        hour = h;
        minute = m;
        type = ty;
    }
}
