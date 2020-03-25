package com.java.toworkservice.mapper;

import com.java.toworkservice.entity.HeathDay;
import com.java.toworkservice.entity.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassNameUserInfoMapper
 * @Description
 * @Author 村头老杨头
 * @Date 2020/3/18 0018 11:20
 * @Version V1.0
 **/
@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
    @Select("<script>" +
            "select * from userinfo" +
            "<where>" +
            "<if test=\"usrname!=null and usrname!=''\">and usrname = #{usrname}</if>" +
            "<if test=\"age!=null and age!=''\" >and age = #{age}</if>" +
            "<if test=\"sex!=null and sex!=''\">and sex = #{sex}</if>" +
            "<if test=\"uno!=null and uno!=''\">and uno=#{uno}</if>" +
            "<if test=\"department!=null and department!=''\">and department=#{department}</if>" +
            "<if test=\"phone!=null and phone!=''\">and phone=#{phone}</if>" +
            "</where>" +
            "</script>")
          List<UserInfo> getUserInfoByPage(Map<String,Object> map);

}
