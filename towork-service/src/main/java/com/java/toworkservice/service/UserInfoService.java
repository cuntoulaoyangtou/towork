package com.java.toworkservice.service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.toworkservice.entity.TodayFriends;
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
import java.util.Map;

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
                throw new BizException("用户名密码为空");
            }
            criteria.andEqualTo("password",userInfo.getPassword());
            UserInfo userInfo1 = userInfoMapper.selectOneByExample(example);
            if(userInfo1 == null){
                throw new BizException("用户名密码错误");
            }
            try {
                String jwt = JwtUtil.createJWT(Constants.JWT_ID, Constants.JWT_ISSUER, JSON.toJSONString(userInfo1), Constants.JWT_TTL);
                RedisUtil.set(jwt,userInfo1);
                return jwt;
            } catch (Exception e) {
                throw new BizException("Token创建失败");
            }
        }
        throw new BizException("用户名密码为空");
    }

    /*
     * 功能描述：通过JWT获得用户信息
     * @param jwt
     * @return 用户对象
     * @Author: 村头老杨头
     * @Date: 2020/3/19 0019 23:10
     *
     */
    public UserInfo getUserInfo(String key){
        Claims claims = JwtUtil.parseJWT(key);
        UserInfo userInfo = (UserInfo) RedisUtil.get(key);
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


            try {
                String jwt = JwtUtil.createJWT(Constants.JWT_ID, Constants.JWT_ISSUER, JSON.toJSONString(userInfo), Constants.JWT_TTL);
                RedisUtil.set(jwt,userInfo);
                return jwt;
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException("JWT创建失败");
            }
        }
        throw  new BizException("非法的数据参数");
    }

    /*
     * 功能描述：修改用户信息
     * @param 用户信息
     * @return jwt
     * @Author: 村头老杨头
     * @Date: 2020/3/19 0019 23:27
     *
     */
    public UserInfo changeUserInfo(UserInfo userInfo)  {
        if (userInfo!=null){
            UserInfo userInfo1;
            if(userInfo.getFace_id()!=null){
                userInfo1 = userInfoMapper.selectByPrimaryKey(userInfo.getUid());
                if(userInfo1.getFace_id()!=null){
                    throw new BizException("已有脸部信息禁止修改");
                }
            }
            userInfoMapper.updateByPrimaryKeySelective(userInfo);

            userInfo1 = userInfoMapper.selectByPrimaryKey(userInfo.getUid());
            RedisUtil.set(Constants.JWT_ID,userInfo1,Constants.JWT_TTL);
            return userInfo1;

        }
        return null;
    }
    public PageInfo<UserInfo> getByPage(Map map, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize,"uid desc");
        List userinfo = userInfoMapper.getUserInfoByPage(map);
        for (int i = 0; i < userinfo.size(); i++) {
            System.out.println(userinfo.get(i));
        }
        PageInfo<UserInfo> tPageInfo = new PageInfo<>(userinfo);

        return  tPageInfo;
    }

}
