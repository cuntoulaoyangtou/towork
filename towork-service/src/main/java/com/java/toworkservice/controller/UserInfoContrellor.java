package com.java.toworkservice.controller;

import com.java.toworkservice.entity.UserInfo;
import com.java.toworkservice.service.UserInfoService;
import entity.Result;
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
    @PostMapping("add")
    public Result addUserInfo(UserInfo userInfo){
        userInfoService.addUserInfo(userInfo);
        return ResultGenerator.genSuccessResult(userInfo);
    }
    @GetMapping("getuser")
    public Result getUserInfo(Integer uid){
        UserInfo userInfoByID = userInfoService.getUserInfoByID(uid);
        return ResultGenerator.genSuccessResult(userInfoByID);
    }
}
