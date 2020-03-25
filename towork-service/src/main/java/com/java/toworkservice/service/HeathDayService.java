package com.java.toworkservice.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.toworkservice.entity.HeathDay;
import com.java.toworkservice.exception.BizException;
import com.java.toworkservice.mapper.HeathDayMapper;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public int sign(HeathDay heathDay){
        if(heathDay!=null){
            if(heathDay.getUid()!=null){
                System.out.println( "<<<<<<<<<<<<<<<<<<<<"+heathDay.getUid());
                Integer uid = heathDay.getUid();
                HeathDay heathIsSing = heathDayMapper.getHeathIsSing(uid);
                System.out.println(heathIsSing);
                if(heathIsSing != null){
                    System.out.println("dfdfdsfdsf");
                    throw new BizException("今日已经打卡");
                }
                System.out.println(">>>>>>>>>>>>>>>>>>");
            }else{
                throw new BizException("非法的用户ID");
            }
            heathDay.setCreate_date(new Date());
           return heathDayMapper.insertSelective(heathDay);
        }

        throw new BizException("非法的数据参数");
    }

    /**
     * 更新健康信息
     * @param heathDay
     * @return
     */
    public  int updata(HeathDay heathDay){
         if(null!=heathDay){
            return heathDayMapper.updateByPrimaryKeySelective(heathDay);
         }
        throw new BizException("非法的数据参数");
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
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<HeathDay> getlist(Map map,Integer pageNum,Integer pageSize){
            PageHelper.startPage(pageNum, pageSize, "hid desc");
            PageInfo<HeathDay> heathDayPageInfo = new PageInfo<>(heathDayMapper.getHeathDayAndUser(map));
           return heathDayPageInfo;
    }



}
