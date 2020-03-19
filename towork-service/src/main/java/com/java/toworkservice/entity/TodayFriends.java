package com.java.toworkservice.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 今日密切接触人员
 */
public class TodayFriends {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY)
    private  Integer tid;
    private  String  names;//接触人员名单


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
