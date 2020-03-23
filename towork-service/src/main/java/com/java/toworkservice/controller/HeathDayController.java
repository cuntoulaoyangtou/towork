package com.java.toworkservice.controller;


import com.github.pagehelper.PageInfo;
import com.java.toworkservice.entity.HeathDay;
import com.java.toworkservice.service.HeathDayService;
import entity.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ResultGenerator;

@RestController
@RequestMapping("api/heath")
public class HeathDayController {
    @Autowired
    HeathDayService heathDayService;

    @PostMapping("sign")
    public Result sign(HeathDay heathDay) {
        heathDayService.sign(heathDay);
        return ResultGenerator.genSuccessResult(heathDay);
    }

    @PostMapping("update")
    public Result update(HeathDay heathDay) {
        heathDayService.updata(heathDay);
        return ResultGenerator.genSuccessResult(heathDay);
    }

    /**
     * 分页条件查询
     * @param heathDay
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("list")
    public Result getByPage(HeathDay heathDay, Integer pageNum, Integer pageSize) {
          PageInfo<HeathDay> pageInfo = heathDayService.getlist(heathDay, pageNum, pageSize);
          return  ResultGenerator.genSuccessResult(pageInfo);
    }
}