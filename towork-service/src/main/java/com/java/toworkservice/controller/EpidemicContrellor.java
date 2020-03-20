package com.java.toworkservice.controller;

import config.APIs;
import entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.RequestUtil;
import utils.ResultGenerator;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * @ClassNameEpidemicContrellor
 * @Description
 * @Author 村头老杨头
 * @Date 2020/3/21 0021 0:58
 * @Version V1.0
 **/
@RestController
@RequestMapping("api/epidemic")
public class EpidemicContrellor {
    @GetMapping("getnews")
    public Result getNews() throws UnsupportedEncodingException {
        Map data = RequestUtil.getData(APIs.API_NEWS);
        System.out.println(data);
        return ResultGenerator.genSuccessResult(data);
    }
    @GetMapping("gettrip")
    public Result getTrip(String arrive,String date,String no,Integer type,Integer page) throws UnsupportedEncodingException {
        String url = APIs.API_TRIP+"?";
        if(arrive != null && !arrive.equals("")){
            url = url +"arrive="+arrive+"&";
        }
        if(date!= null && !date.equals("")){
            url = url + "date="+date+"&";
        }
        if(type != null && type>0){
            url = url+"type="+type+"&";
        }
        if(no != null && !no.equals("")){
            url = url+"no="+no+"&";
        }
        url = url +"page="+page;
        System.out.println(url);
        Map data = RequestUtil.getData(url);
        System.out.println(data);
        return ResultGenerator.genSuccessResult(data);
    }
}