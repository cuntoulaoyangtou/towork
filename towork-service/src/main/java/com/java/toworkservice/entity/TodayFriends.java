package com.java.toworkservice.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 今日密切接触人员
 */
public class TodayFriends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer tid;
    private Integer uid;
    private  String  names;//接触人员名单
    private Date   date;//填写日期

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

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
