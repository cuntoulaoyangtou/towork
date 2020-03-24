package com.java.toworkservice.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.toworkservice.entity.TodayFriends;
import com.java.toworkservice.exception.BizException;
import com.java.toworkservice.mapper.TodayFrendsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TodyFrendsService {
    @Autowired
    private TodayFrendsMapper todayFrendsMapper;

    /**
     * 增加密切接触人员
     * @param todayFriends
     * @return
     */
    public String add(TodayFriends todayFriends){

        if(null!=todayFriends){
            TodayFriends todayFriends1=todayFrendsMapper.getTodyFrendsIsSing(todayFriends.getUid());
            if(todayFriends==null){
                todayFriends.setDate(new Date());
                todayFrendsMapper.insertSelective(todayFriends);
            }else{
                throw  new BizException("今天已经填写过了");
            }

        }

        throw  new BizException("创建失败");
    }

    /**
     * 更改密切接触人员
     * @param todayFriends
     * @return
     */
    public String update(TodayFriends todayFriends){
        if(null!=todayFriends){
            todayFrendsMapper.updateByPrimaryKey(todayFriends);
        }
        return null;
    }



   public PageInfo<TodayFriends> getByPage(Map map, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize,"date desc");
        List todyFrends = todayFrendsMapper.getTodyFrends(map);
       for (int i = 0; i < todyFrends.size(); i++) {
           System.out.println(todyFrends.get(i));
       }
       PageInfo<TodayFriends> tPageInfo = new PageInfo<>(todyFrends);

       return  tPageInfo;
   }








}
