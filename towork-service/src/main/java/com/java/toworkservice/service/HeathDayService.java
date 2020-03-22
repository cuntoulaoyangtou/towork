package com.java.toworkservice.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.toworkservice.entity.HeathDay;
import com.java.toworkservice.exception.BizException;
import com.java.toworkservice.mapper.HeathDayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HeathDayService {
    @Autowired
    private HeathDayMapper heathDayMapper;

    /**
     * 每日打卡存档  增加
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

    /**
     * 更新健康信息
     * @param heathDay
     * @return
     */
    public  String updata(HeathDay heathDay){
         if(null!=heathDay){
            heathDayMapper.updateByPrimaryKeySelective(heathDay);
         }
        return null;
    }

    /**
     * 按照ID删除一条信息
     * @param hid
     * @return
     */
    public int delete(Integer hid){
        int i=0;
        if(hid>0){
         i=  heathDayMapper.deleteByPrimaryKey(hid);
        }
        return i;
    }

    /**
     * 分页条件查询
     * @param heathDay
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<HeathDay> getlist(HeathDay heathDay,Integer pageNum,Integer pageSize){

        Example example=new Example(HeathDay.class);

        Example.Criteria criteria = example.createCriteria();
            if(heathDay.getUid()!=null){
              criteria.andEqualTo("uid",heathDay.getUid());
            }
            if(heathDay.getTemperature()!=0){
                criteria.andGreaterThan("temperature",heathDay.getTemperature());
            }
            if(heathDay.getCrosswh()!=null){
                criteria.andEqualTo("crosswh",heathDay.getCrosswh());

            }
            if(heathDay.getIshot()!=null){
                criteria.andEqualTo("ishot",heathDay.getIshot());
            }
            if(heathDay.getIskeke()!=null){
            criteria.andEqualTo("iskeke",heathDay.getIskeke());
           }
            if(heathDay.getTowuhan()!=null){
            criteria.andEqualTo("towuhan",heathDay.getTowuhan());
           }
            if(heathDay.getFirends_tor_cwh()!=null){
            criteria.andEqualTo("firends_tor_cwh",heathDay.getFirends_tor_cwh());
           }
            if(heathDay.getIncomadd()!=null){
                criteria.andEqualTo("incomadd",heathDay.getIncomadd());
            }
            PageHelper.startPage(pageNum, pageSize, "hid desc");
            PageInfo<HeathDay> heathDayPageInfo = new PageInfo<>(heathDayMapper.selectByExample(example));

           return heathDayPageInfo;
    }



}
