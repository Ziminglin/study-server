package com.ming.study.handler;

import com.alibaba.fastjson.JSON;
import com.ming.study.entity.RespBean;
import com.ming.study.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: ziming
 * @Time: 2022/10/8 16:33
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 给前端返回ResponseResult的json数据
//        ResponseResult responseResult = new ResponseResult(HttpStatus.FORBIDDEN.value(), "您没有权限!");
        RespBean respResult = RespBean.denied("您没有权限!");
        String json = JSON.toJSONString(respResult);

        WebUtils.renderString(response,json);
    }
}
