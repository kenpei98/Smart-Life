package com.example.smartsleep;

import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("time")
    public String time;

    @SerializedName("event")
    public String contains;

    @SerializedName("userid")
    public int userId;

    @SerializedName("id")
    public int eventId;

    public Event(int userId, int eventId, String time, String contains)
    {
        this.userId = userId;
        this.time = time;
        this.contains = contains;
        this.eventId = eventId;
    }
}
