package com.java.toworkservice.mapper;

import com.java.toworkservice.entity.HeathDay;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface HeathDayMapper extends Mapper<HeathDay> {
    @Select("<script>" +
            "select * from heathday" +
            "<where>" +
            "<if test=\"start_date!=null and start_date!=''\">and create_date &lt; #{strat_date}</if>" +
            "<if test=\"end_date!=null and end_date!=''\" >and create_date &gt; #{end_date}</if>" +
            "<if test=\"temperature!=null and temperature!=''\">and temperature &gt; #{temperature}</if>" +
            "<if test=\"ishot!=null and ishot!=''\">and ishot=#{ishot}</if>" +
            "<if test=\"iskeke!=null and iskeke!=''\">and iskeke=#{iskeke}</if>" +
            "<if test=\"towuhan!=null and towuhan!=''\">and towuhan=#{towuhan}</if>" +
            "<if test=\"crosswh!=null and crosswh!=''\" >and crosswh=#{crosswh}</if>" +
            "<if test=\"firends_tor_cwh!=null and firends_tor_cwh!=''\">and firends_tor_cwh=#{firends_tor_cwh}</if>" +
            "<if test=\"incomadd!=null and incomadd!=''\">and incomadd=#{incomadd}</if>" +
            "</where>" +
            "</script>")
    @Results(id = "u_id", value = {
            @Result(column = "uid",property = "uid"),
            @Result(column = "uid",property = "userInfo", one = @One(select = "com.java.toworkservice.mapper.UserInfoMapper.selectByPrimaryKey")),
    })
    List<HeathDay> getHeathDayAndUser(Map<String,Object> map);
    @Select("<script>select * from heathday where to_days(create_date) = to_days(now()) <if test='uid>0'>and uid=#{uid}</if></script>")
    HeathDay getHeathIsSing(@Param(value = "uid") Integer uid);

    @Insert("insert into heathday (uid,create_date,temperature,ishot,iskeke,towuhan,crosswh,firends_tor_cwh,incomadd,other ) " +
            "values (#{uid},now(),#{temperature},#{ishot},#{iskeke},#{towuhan},#{crosswh},#{firends_tor_cwh},#{incomadd},#{other})")
    int addHeathDay(Integer uid,Double temperature,Boolean ishot,Boolean iskeke,Boolean towuhan,Boolean crosswh,Boolean firends_tor_cwh,Boolean incomadd,String other);

}
