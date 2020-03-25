package com.java.toworkservice.entity;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.*;
import java.util.Date;

/**
 * 每日健康打卡内容
 */
@Table(name = "heathday")
@NameStyle(Style.normal)
public class HeathDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hid;
    private Integer uid;    //用户id
    private Date create_date;   //创建时间
    private Date update_date;   //修改时间
    private Double temperature;//体温
    private Boolean ishot;//是否发烧 1是 2否
    private Boolean iskeke;//是否咳嗽 1是 2否
    private Boolean towuhan;//是否到过武汉 1是 2否
    private Boolean crosswh;//是否途径武汉 1是 2否
    private Boolean firends_tor_cwh;//密切接触人员是否去过或者途径武汉 1是 2否
    private Boolean incomadd;//是否在企业所在地 1是 2否
    private String other; //其他信息
    private String update_reason;   //修改原因
    @Transient
    private UserInfo userInfo;

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Boolean getIshot() {
        return ishot;
    }

    public void setIshot(Boolean ishot) {
        this.ishot = ishot;
    }

    public Boolean getIskeke() {

        return iskeke;
    }

    public void setIskeke(Boolean iskeke) {
        this.iskeke = iskeke;
    }

    public Boolean getTowuhan() {
        return towuhan;
    }

    public void setTowuhan(Boolean towuhan) {
        this.towuhan = towuhan;
    }

    public Boolean getCrosswh() {
        return crosswh;
    }

    public void setCrosswh(Boolean crosswh) {
        this.crosswh = crosswh;
    }

    public Boolean getFirends_tor_cwh() {
        return firends_tor_cwh;
    }

    public void setFirends_tor_cwh(Boolean firends_tor_cwh) {
        this.firends_tor_cwh = firends_tor_cwh;
    }

    public Boolean getIncomadd() {
        return incomadd;
    }

    public void setIncomadd(Boolean incomadd) {
        this.incomadd = incomadd;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getUpdate_reason() {
        return update_reason;
    }

    public void setUpdate_reason(String update_reason) {
        this.update_reason = update_reason;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "HeathDay{" +
                "hid=" + hid +
                ", uid=" + uid +
                ", create_date=" + create_date +
                ", update_date=" + update_date +
                ", temperature=" + temperature +
                ", ishot=" + ishot +
                ", iskeke=" + iskeke +
                ", towuhan=" + towuhan +
                ", crosswh=" + crosswh +
                ", firends_tor_cwh=" + firends_tor_cwh +
                ", incomadd=" + incomadd +
                ", other='" + other + '\'' +
                ", update_reason='" + update_reason + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
