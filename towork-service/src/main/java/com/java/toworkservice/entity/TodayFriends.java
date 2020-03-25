package com.java.toworkservice.entity;

import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.*;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date   date;//填写日期
    @Transient
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
