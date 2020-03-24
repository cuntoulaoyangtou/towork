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
            Example example=new Example(TodayFriends.class);
             Example.Criteria criteria = example.createCriteria();
             if(todayFriends.getUid()!=null){
                 criteria.andEqualTo("uid",todayFriends.getUid());
             }
             if(todayFriends.getDate()!=null){
                 criteria.andEqualTo("date",todayFriends.getDate());

             }
            TodayFriends todayFriends1=todayFrendsMapper.selectOneByExample(example);
            if(todayFriends==null){
                todayFrendsMapper.insertSelective(todayFriends);
            }else{
                throw  new BizException("今天已经填写过了，如果想增加密切接触人员，请更改");
            }

        }

        return  null;
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
