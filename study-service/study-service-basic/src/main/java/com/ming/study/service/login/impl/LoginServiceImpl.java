package com.ming.study.service.login.impl;

import com.ming.study.entity.RespBean;
import com.ming.study.entity.User;
import com.ming.study.service.login.LoginService;
import com.ming.study.utils.JwtUtil;
import com.ming.study.utils.LoginUser;
import com.ming.study.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: ziming
 * @Time: 2022/10/5 22:48
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisCache redisCache;

    /**
     * 登录验证
     * @param user
     * @return
     */
    @Override
    public Map<String,Object> login(User user) {
        // 3.使用ProviderManager auth方法进行验证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // 校验失败了
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误!");
        }


        // 4.生成jwt传递到前端
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwt);
        map.put("userId", userId);

        // 5.系统用户信息存入redis
        redisCache.setCacheObject("login:" + userId, loginUser);
//        return new ResponseResult(HttpStatus.OK.value(), "登录成功", map);
        return map;
    }

    @Override
    public RespBean logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userId);


//        return new ResponseResult(200, "退出成功!");
        return RespBean.ok("退出成功");
    }
}
