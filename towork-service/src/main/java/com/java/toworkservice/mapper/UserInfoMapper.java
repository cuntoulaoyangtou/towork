package com.java.toworkservice.mapper;

import com.java.toworkservice.entity.UserInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassNameUserInfoMapper
 * @Description
 * @Author 村头老杨头
 * @Date 2020/3/18 0018 11:20
 * @Version V1.0
 **/
@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {

}
