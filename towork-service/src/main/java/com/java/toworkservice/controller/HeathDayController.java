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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("list")
    public Result getByPage(HttpServletRequest request, Integer pageNum, Integer pageSize) {

        Map map = new HashMap();
        map.put("iskeke",request.getParameter("iskeke"));
        map.put("ishot",request.getParameter("ishot"));
        map.put("towuhan",request.getParameter("towuhan"));
        map.put("croswh",request.getParameter("croswh"));
        map.put("firends_tor_cwh",request.getParameter("firends_tor_cwh"));
        map.put("incomadd",request.getParameter("incomadd"));
        map.put("start_date",request.getParameter("start_date"));
        map.put("end_date",request.getParameter("end_date"));
          PageInfo<HeathDay> pageInfo = heathDayService.getlist(map, pageNum, pageSize);
          return  ResultGenerator.genSuccessResult(pageInfo);
    }
}