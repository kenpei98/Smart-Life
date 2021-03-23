package com.example.smartsleep;

public class OnlineAlarm {
    public int id;
    public int userid;
    public String time;
    public int ouserid;
    public String message;
    /*
    建议你改成int
     */
    public String type;

    public OnlineAlarm(int mid,int muserid,String mtime,int mouserid,String mmessage,String mtype){
        id = mid;
        userid = muserid;
        time = mtime;
        ouserid = mouserid;
        message = mmessage;
        type = mtype;
    }
}
