package com.ming.study.filter;

import com.alibaba.fastjson.JSON;
import com.ming.study.entity.RespBean;
import com.ming.study.utils.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: ziming
 * @Time: 2022/11/1 18:39
 */
@Component
public class KaptchaAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {

        if ("POST".equals(req.getMethod()) && req.getServletPath().contains("/user/login")){
            // 登录请求
            String code = req.getParameter("code");
            System.out.println("code = " + code);
            String index_code = (String) req.getSession().getAttribute("index_code");
            System.out.println("index_code = " + index_code);
            if (!code.equalsIgnoreCase(index_code)){
                // 验证码错误
                WebUtils.renderString(resp, JSON.toJSONString(RespBean.error("验证码错误!")));
            }else {
                filterChain.doFilter(req,resp);
            }

        }else {
            filterChain.doFilter(req,resp);
        }
    }
}
