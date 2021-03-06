package com.java.toworkservice.controller;

import com.github.pagehelper.PageInfo;
import com.java.toworkservice.entity.TodayFriends;
import com.java.toworkservice.service.TodyFrendsService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utils.ResultGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/frens")
public class TodayFrendsController {
    @Autowired
    TodyFrendsService todyFrendsService;

    /**
     * 条件分页查询
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping("list")
    public Result getByPage(HttpServletRequest request, Integer pageNum, Integer pageSize){
           Map map=new HashMap();
            map.put("start_date",request.getParameter("start_date"));
            map.put("end_date",request.getParameter("end_date"));
            map.put("name",request.getParameter("name"));
            PageInfo<TodayFriends> byPage = todyFrendsService.getByPage(map, pageNum, pageSize);
          return ResultGenerator.genSuccessResult(byPage);
    }

   @PostMapping("add")
    public  Result add(Integer uid,String names){
       System.out.println(uid+"\t"+names);
        String res=todyFrendsService.add(uid,names);
        return  ResultGenerator.genSuccessResult("添加成功");
   }
   @PostMapping("up")
    public Result updata(TodayFriends todayFriends){
        String res=todyFrendsService.update(todayFriends);

        return  ResultGenerator.genSuccessResult(res);
   }




}
