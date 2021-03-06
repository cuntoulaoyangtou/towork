package com.java.toworkservice.exception;


import com.alibaba.fastjson.JSON;
import entity.Result;
import entity.ResultCodeEnum;
import io.jsonwebtoken.ExpiredJwtException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.ResultGenerator;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassNameGlobalException
 * @Description
 * @Author 村头老杨头
 * @Date 2020/2/19 0019 7:46
 * @Version V1.0
 **/
@ControllerAdvice
public class GlobalException {
    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result bizExceptionHandler(HttpServletRequest req, BizException e){
        logger.error("发生业务异常！原因是：{} {}",e.getErrorMsg(), JSON.toJSONString(e.getData()));
        return ResultGenerator.genFailResult(e.getMessage()).setData(e.getData());
    }



    /**
     * SQL唯一约束的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, DuplicateKeyException e){
        logger.error("SQL唯一约束异常！原因是:{}",e);
        return ResultGenerator.genFailResult("SQL唯一约束异常");
    }


    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, ExpiredJwtException e){
        logger.error("Jwt异常过期:{}",e.getMessage());
        return ResultGenerator.genFailResult("Token失效").setCode(ResultCodeEnum.FORBIDDEN);
    }




    /**
     * MyBatis异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MyBatisSystemException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, MyBatisSystemException e){
        logger.error("MyBatis异常！原因是: {}",e);
        return ResultGenerator.genFailResult("MyBatis异常");
    }

    /**
     * 绑定的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, BindException e){
        logger.error("绑定异常！原因是:{}",e);
        return ResultGenerator.genFailResult("API接口参数错误");
    }
    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e){
        logger.error("发生空指针异常！原因是:{}",e);
        return ResultGenerator.genFailResult("空指针异常");
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, Exception e){
        logger.error("未知异常！原因是:{}",e);
        return ResultGenerator.genFailResult("未知异常");
    }
}
