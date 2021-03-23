package com.example.smartsleep;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("password")
    public String password;
    @SerializedName("gender")
    public String gender;
    @SerializedName("email")
    public String email;
    @SerializedName("type")
    public int type;

    public User(int tid,String tname, String pw, String gd, String em, int ty){
        id = tid;
        name = tname;
        password = pw;
        gender = gd;
        email = em;
        type = ty;
    }
}
