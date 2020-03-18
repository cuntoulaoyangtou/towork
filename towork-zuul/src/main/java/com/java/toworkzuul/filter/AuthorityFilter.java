package com.java.toworkzuul.filter;

import com.alibaba.fastjson.JSON;
import config.Constants;
import entity.ResultCodeEnum;
import entity.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.core.annotation.Order;
import utils.JwtUtil;
import utils.ResultGenerator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;


@Order(2)
@WebFilter(filterName = "authority",urlPatterns = "/admin/*")
public class AuthorityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("authority");
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String method = request.getMethod();
        if(!"OPTIONS".equals(method)){
            String token = request.getHeader(Constants.TOKEN_PREFIX+"-"+Constants.TOKEN);
            try {
                JwtUtil.parseJWT(token);
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (ExpiredJwtException e) {
                servletResponse.setCharacterEncoding("utf-8");
                HttpServletResponse response = (HttpServletResponse)servletResponse;
                response.addHeader("Access-Control-Allow-Origin","*");
                response.addHeader("Content-Type","application/json;charset=UTF-8");
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(JSON.toJSONString(ResultGenerator.genFailResult("Token失效").setCode(ResultCodeEnum.FORBIDDEN)).getBytes());
                outputStream.flush();
            }
        }else{
            filterChain.doFilter(request,servletResponse);
        }
    }



    @Override
    public void destroy() {

    }
}
