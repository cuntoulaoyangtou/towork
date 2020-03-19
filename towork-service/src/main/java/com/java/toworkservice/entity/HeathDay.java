package com.java.toworkservice.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 每日健康打卡内容
 */
public class HeathDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hid;
    private  double temperature;//体温
    private  Integer ishot;//是否发烧 1是 2否
    private  Integer iskeke;//是否咳嗽 1是 2否
    private  Integer towuhan;//是否到过武汉 1是 2否
    private  Integer crosswh;//是否途径武汉 1是 2否
    private  Integer firendsTorCwh;//密切接触人员是否去过或者途径武汉 1是 2否
    private  Integer incomadd;//是否在企业所在地 1是 2否


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

    public Integer getIshot() {
        return ishot;
    }

    public void setIshot(Integer ishot) {
        this.ishot = ishot;
    }

    public Integer getIskeke() {
        return iskeke;
    }

    public void setIskeke(Integer iskeke) {
        this.iskeke = iskeke;
    }

    public Integer getTowuhan() {
        return towuhan;
    }

    public void setTowuhan(Integer towuhan) {
        this.towuhan = towuhan;
    }

    public Integer getCrosswh() {
        return crosswh;
    }

    public void setCrosswh(Integer crosswh) {
        this.crosswh = crosswh;
    }

    public Integer getFirendsTorCwh() {
        return firendsTorCwh;
    }

    public void setFirendsTorCwh(Integer firendsTorCwh) {
        this.firendsTorCwh = firendsTorCwh;
    }

    public Integer getIncomadd() {
        return incomadd;
    }

    public void setIncomadd(Integer incomadd) {
        this.incomadd = incomadd;
    }
}
