package com.java.toworkservice.controller;

import com.java.toworkservice.entity.UserInfo;
import com.java.toworkservice.service.UserInfoService;
import entity.Result;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ResultGenerator;

/**
 * @ClassNameUserInfoContrellor
 * @Description
 * @Author 村头老杨头
 * @Date 2020/3/18 0018 11:29
 * @Version V1.0
 **/
@RestController
@RequestMapping("api/user")
public class UserInfoContrellor {
    @Autowired
    UserInfoService userInfoService;
    @PostMapping("dologin")
    public Result doLogin(UserInfo userInfo){
        String dologin = userInfoService.dologin(userInfo);
        return ResultGenerator.genSuccessResult(dologin);
    }
    @PostMapping("doregister")
    public Result doRegister(UserInfo userInfo){
        String s = userInfoService.doRegister(userInfo);
        return ResultGenerator.genSuccessResult(s);
    }
    @PostMapping("change")
    public Result changeUserInfo(UserInfo userInfo){
        UserInfo userInfo1 = userInfoService.changeUserInfo(userInfo);
        return ResultGenerator.genSuccessResult(userInfo1);
    }
    @GetMapping("getuserinfo")
    public Result getUserInfo(String key){
        UserInfo userInfo = userInfoService.getUserInfo(key);
        return ResultGenerator.genSuccessResult(userInfo);
    }
}
