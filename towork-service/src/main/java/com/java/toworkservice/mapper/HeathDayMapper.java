package com.java.toworkservice.mapper;

import com.java.toworkservice.entity.HeathDay;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface HeathDayMapper extends Mapper<HeathDay> {
    @Select("<script>" +
            "select * from heathday" +
            "<where>" +
            "<if test='start_date!=null'>and start_date &lt; #{create_date}</if>" +
            "<if test='end_date!=null'>and end_date &gt; #{create_date}</if>" +
            "<if test='temperature!=null'>and temperature &gt; #{temperature}</if>" +
            "<if test='ishot!=null'>and ishot=#{ishot}</if>" +
            "<if test='iskeke!=null'>and iskeke=#{iskeke}</if>" +
            "<if test='towuhan!=null'>and towuhan=#{towuhan}</if>" +
            "<if test='crosswh!=null'>and crosswh=#{crosswh}</if>" +
            "<if test='firends_tor_cwh!=null'>and firends_tor_cwh=#{firends_tor_cwh}</if>" +
            "<if test='incomadd!=null'>and incomadd=#{incomadd}</if>" +
            "</where>" +
            "</script>")
    @Results(id = "q_all", value = {
            @Result(column = "uid",property = "uid"),
            @Result(column = "uid",property = "userInfo", one = @One(select = "com.java.toworkservice.mapper.UserInfoMapper.selectByPrimaryKey")),
    })
    List<HeathDay> getHeathDayAndUser(Map<String,Object> map);

}
