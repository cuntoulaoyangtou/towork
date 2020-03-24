package com.java.toworkservice.entity;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 今日密切接触人员
 */
@Table(name="todyfrends")
@NameStyle(Style.normal)
public class TodayFriends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer tid;
    private  Integer uid;
    private  String  names;//接触人员名单
    private  Date   date;//填写日期
    private UserInfo userInfo;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "TodayFriends{" +
                "tid=" + tid +
                ", uid=" + uid +
                ", names='" + names + '\'' +
                ", date=" + date +
                ", userInfo=" + userInfo +
                '}';
    }
}
