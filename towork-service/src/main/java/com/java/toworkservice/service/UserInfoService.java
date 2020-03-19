package com.java.toworkservice.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.toworkservice.entity.UserInfo;
import com.java.toworkservice.exception.BizException;
import com.java.toworkservice.mapper.UserInfoMapper;
import com.java.toworkservice.utils.RedisUtil;
import config.Constants;
import io.jsonwebtoken.Claims;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import utils.JwtUtil;

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

    /*
     * 功能描述：用户登录
     * @param
     * @return token
     * @Author: 村头老杨头
     * @Date: 2020/3/19 0019 22:30
     *
     */
    public String dologin(UserInfo userInfo){
        if (userInfo != null && userInfo.getPassword()!=null) {
            Example example = new Example(UserInfo.class);
            Example.Criteria criteria = example.createCriteria();
            if(userInfo.getPhone() !=null){
                criteria.andEqualTo("phone",userInfo.getPhone());
            }else if(userInfo.getUsername() != null){
                criteria.andEqualTo("username",userInfo.getUsername());
            }else{
                return null;
            }
            criteria.andEqualTo("password",userInfo.getPassword());
            UserInfo userInfo1 = userInfoMapper.selectOneByExample(example);
            try {
                entity.UserInfo userInfo2 = new entity.UserInfo(userInfo1.getUid(), userInfo1.getUsername(), userInfo1.getPhone(), userInfo1.getUno(), userInfo1.getAge(), userInfo1.getSex(), userInfo1.getAddress(), userInfo1.getDepartment(), userInfo1.getGrade(), userInfo1.getAvatar(), userInfo1.getFace_id());
                String jwt = JwtUtil.createJWT(Constants.JWT_ID, Constants.JWT_ISSUER, JSON.toJSONString(userInfo2), Constants.JWT_TTL);
                RedisUtil.set(Constants.JWT_ID,userInfo2,Constants.JWT_TTL);
                return jwt;
            } catch (Exception e) {
                throw new BizException("JWT创建失败");
            }
        }
        return null;
    }

    /*
     * 功能描述：通过JWT获得用户信息
     * @param jwt
     * @return 用户对象
     * @Author: 村头老杨头
     * @Date: 2020/3/19 0019 23:10
     *
     */
    public entity.UserInfo getUserInfo(String key){
        Claims claims = JwtUtil.parseJWT(key);
        String subject = claims.getSubject();
        entity.UserInfo userInfo = JSON.parseObject(subject, entity.UserInfo.class);
        return userInfo;
    }

    /*
     * 功能描述：注册
     * @param 手机号 密码
     * @return  返回JwT
     * @Author: 村头老杨头
     * @Date: 2020/3/19 0019 23:12
     *
     */
    public String doRegister(UserInfo userInfo){
        if(userInfo!=null && userInfo.getPhone() !=null && userInfo.getPassword()!=null){
            if(!userInfo.getPhone().matches(Constants.REGEX_PHONE)){
                throw new BizException("手机号格式不正确");
            }
            if(userInfo.getPassword().length()!=32){
                throw new BizException("密码格式不正确");
            }
            try {
                userInfoMapper.insertSelective(userInfo);
            }catch (DuplicateKeyException e){
                throw new BizException("此用户已存在");
            }

            entity.UserInfo userInfo1 = new entity.UserInfo(userInfo.getUid(), userInfo.getPhone());
            try {
                String jwt = JwtUtil.createJWT(Constants.JWT_ID, Constants.JWT_ISSUER, JSON.toJSONString(userInfo1), Constants.JWT_TTL);
                RedisUtil.set(Constants.JWT_ID,userInfo1,Constants.JWT_TTL);
                return jwt;
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException("JWT创建失败");
            }
        }
        return null;
    }

    /*
     * 功能描述：修改用户信息
     * @param 用户信息
     * @return jwt
     * @Author: 村头老杨头
     * @Date: 2020/3/19 0019 23:27
     *
     */
    public String changeUserInfo(UserInfo userInfo)  {
        if (userInfo!=null){
            UserInfo userInfo1;
            if(userInfo.getFace_id()!=null){
                userInfo1 = userInfoMapper.selectByPrimaryKey(userInfo.getUid());
                if(userInfo1.getFace_id()!=null){
                    throw new BizException("已有脸部信息禁止修改");
                }
            }
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
            entity.UserInfo userInfo2 = new entity.UserInfo(userInfo.getUid(), userInfo.getUsername(), userInfo.getPhone(), userInfo.getUno(), userInfo.getAge(), userInfo.getSex(), userInfo.getAddress(), userInfo.getDepartment(), userInfo.getGrade(), userInfo.getAvatar(), userInfo.getFace_id());
            try {
                String jwt = JwtUtil.createJWT(Constants.JWT_ID, Constants.JWT_ISSUER, JSON.toJSONString(userInfo2), Constants.JWT_TTL);
                RedisUtil.set(Constants.JWT_ID,userInfo2,Constants.JWT_TTL);
                return jwt;
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException("JWT创建失败");
            }
        }
        return null;
    }

}
