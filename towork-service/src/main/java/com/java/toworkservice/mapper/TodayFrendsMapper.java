package com.java.toworkservice.mapper;


import com.java.toworkservice.entity.HeathDay;
import com.java.toworkservice.entity.TodayFriends;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface TodayFrendsMapper extends Mapper<TodayFriends> {
    @Select("<script>" +
            "select * from todyfrends" +
            "<where>" +
            "<if test=\"start_date!=null and start_date!=''\">and date &lt; #{start_date}</if>" +
            "<if test=\"end_date!=null and end_date!=''\">and date &gt; #{end_date}</if>" +
            "<if test=\"name!=null and name!=''\">and uid = select uid from userinfo where username = #{name}</if>" +
            "</where>" +
            "</script>")
    @Results(id = "u_id", value = {
            @Result(column = "uid", property = "uid"),
            @Result(column = "uid", property = "userInfo", one = @One(select = "com.java.toworkservice.mapper.UserInfoMapper.selectByPrimaryKey")),
    })
    List<TodayFriends> getTodyFrends(Map<String, Object> map);


}
