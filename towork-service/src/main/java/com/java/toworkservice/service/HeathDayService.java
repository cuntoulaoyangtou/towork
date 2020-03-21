package com.java.toworkservice.service;

import com.java.toworkservice.entity.HeathDay;
import com.java.toworkservice.exception.BizException;
import com.java.toworkservice.mapper.HeathDayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import tk.mybatis.mapper.entity.Example;

public class HeathDayService {
    @Autowired
    private HeathDayMapper heathDayMapper;

    /**
     * 每日打卡存档
     * @param heathDay
     * @return
     */
    public String sign(HeathDay heathDay){
        if(heathDay!=null){
            Example example=new Example(HeathDay.class);

             Example.Criteria criteria = example.createCriteria();
            try {
                heathDayMapper.insertSelective(heathDay);
            }catch (DuplicateKeyException e){
                throw new BizException("今日已打卡");
            }
        }

        return null;
    }









}
