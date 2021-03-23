package com.example.smartsleep;

public class Relationship {
    public int id;
    public int ouserid;
    public int userid;
    public int approved;

    public Relationship(int tid,int in_ouserid,int in_userid,int in_approved){
        id = tid;
        ouserid = in_ouserid;
        userid = in_userid;
        approved = in_approved;
    }
}
