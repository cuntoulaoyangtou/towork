package com.java.toworkservice.controller;

import com.github.pagehelper.PageInfo;
import com.java.toworkservice.entity.TodayFriends;
import com.java.toworkservice.service.TodyFrendsService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ResultGenerator;

@RestController
@RequestMapping("api/frens")
public class TodayFrendsController {
    @Autowired
    TodyFrendsService todyFrendsService;

    /**
     * 条件分页查询
     * @param todayFriends
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("list")
    public Result getByPage(TodayFriends todayFriends,Integer pageNum,Integer pageSize){
         PageInfo<TodayFriends> byPage = todyFrendsService.getByPage(todayFriends, pageNum, pageSize);

        return ResultGenerator.genSuccessResult(byPage);
    }

   @PostMapping("add")
    public  Result add(TodayFriends todayFriends){
        String res=todyFrendsService.add(todayFriends);
        return  ResultGenerator.genSuccessResult(res);
   }
   @PostMapping("up")
    public Result updata(TodayFriends todayFriends){
        String res=todyFrendsService.update(todayFriends);

        return  ResultGenerator.genSuccessResult(res);
   }




}
