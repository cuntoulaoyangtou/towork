package com.java.toworkservice.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.toworkservice.entity.UserInfo;
import com.java.toworkservice.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassNameUserInfoService
 * @Description
 * @Author 村头老杨头
 * @Date 2020/3/18 0018 11:24
 * @Version V1.0
 **/
@Service
@Transactional
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    public int addUserInfo(UserInfo userInfo){
        return userInfoMapper.insertSelective(userInfo);
    }
    public UserInfo getUserInfoByID(Integer uid){
        return userInfoMapper.selectByPrimaryKey(uid);
    }
    public List<UserInfo> getUserInfos(){
        return userInfoMapper.selectAll();
    }
    public PageInfo<UserInfo> getUserInfoByPage(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<UserInfo> userInfos = userInfoMapper.selectAll();
        return new PageInfo<UserInfo>(userInfos);
    }
}
