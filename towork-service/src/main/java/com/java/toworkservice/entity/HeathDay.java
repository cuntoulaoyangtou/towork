package com.java.toworkservice.entity;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private double temperature;//体温
    private boolean ishot;//是否发烧 1是 2否
    private boolean iskeke;//是否咳嗽 1是 2否
    private boolean towuhan;//是否到过武汉 1是 2否
    private boolean crosswh;//是否途径武汉 1是 2否
    private boolean firends_tor_cwh;//密切接触人员是否去过或者途径武汉 1是 2否
    private boolean incomadd;//是否在企业所在地 1是 2否
    private String other; //其他信息
    private String update_reason;   //修改原因


    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
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

    public boolean isIshot() {
        return ishot;
    }

    public void setIshot(boolean ishot) {
        this.ishot = ishot;
    }

    public boolean isIskeke() {
        return iskeke;
    }

    public void setIskeke(boolean iskeke) {
        this.iskeke = iskeke;
    }

    public boolean isTowuhan() {
        return towuhan;
    }

    public void setTowuhan(boolean towuhan) {
        this.towuhan = towuhan;
    }

    public boolean isCrosswh() {
        return crosswh;
    }

    public void setCrosswh(boolean crosswh) {
        this.crosswh = crosswh;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public boolean isFirends_tor_cwh() {
        return firends_tor_cwh;
    }

    public void setFirends_tor_cwh(boolean firends_tor_cwh) {
        this.firends_tor_cwh = firends_tor_cwh;
    }

    public String getUpdate_reason() {
        return update_reason;
    }

    public void setUpdate_reason(String update_reason) {
        this.update_reason = update_reason;
    }

    public boolean isIncomadd() {
        return incomadd;
    }

    public void setIncomadd(boolean incomadd) {
        this.incomadd = incomadd;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
