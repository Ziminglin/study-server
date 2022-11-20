package com.ming.study.handler;

import com.alibaba.fastjson.JSON;
import com.ming.study.entity.RespBean;
import com.ming.study.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: ziming
 * @Time: 2022/10/8 16:28
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 给前端返回ResponseResult的json数据
//        ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误,请重新输入");
        String exception = authException.getClass().getSimpleName();
        RespBean respResult = null;
        if (exception.equals("BadCredentialsException")) {
            respResult = RespBean.loginFail(authException.getMessage());
        }else if (exception.equals("InsufficientAuthenticationException")){
            respResult = RespBean.notLogin("登录失效,请重新登录!");
        }else if (exception.equals("InternalAuthenticationServiceException")){
            respResult = RespBean.loginFail(authException.getMessage());
        }
        String json = JSON.toJSONString(respResult);
        WebUtils.renderString(response, json);
    }
}
