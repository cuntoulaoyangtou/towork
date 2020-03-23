package com.java.toworkservice.controller;

import com.alibaba.fastjson.JSON;
import com.java.toworkservice.entity.UserInfo;
import com.java.toworkservice.exception.BizException;
import com.java.toworkservice.service.UserInfoService;
import com.java.toworkservice.utils.RedisUtil;
import config.Constants;
import entity.Result;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JwtUtil;
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
        System.out.println("leile");
        System.out.println(userInfo);
        String dologin = userInfoService.dologin(userInfo);
        System.out.println(userInfo);
        System.out.println("jwt\n"+dologin);
        return ResultGenerator.genSuccessResult(dologin);
    }
    @PostMapping("doregister")
    public Result doRegister(UserInfo userInfo){
        String s = userInfoService.doRegister(userInfo);
        return ResultGenerator.genSuccessResult(s);
    }
    @GetMapping("getkey")
    public Result getkey(String key) {
        UserInfo userInfo = (UserInfo)RedisUtil.get(key);
        String jwt = null;
        try {
            jwt = JwtUtil.createJWT(Constants.JWT_ID, Constants.JWT_ISSUER, JSON.toJSONString(userInfo), Constants.JWT_TTL);
        } catch (Exception e) {

            e.printStackTrace();
            throw new BizException("Token创建失败");
        }
        return ResultGenerator.genSuccessResult(jwt);

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
