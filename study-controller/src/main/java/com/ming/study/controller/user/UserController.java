package com.ming.study.controller.user;

import com.alibaba.fastjson.JSON;
import com.ming.study.entity.RespBean;
import com.ming.study.entity.User;
import com.ming.study.service.login.LoginService;
import com.ming.study.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: ziming
 * @Time: 2022/10/23 22:52
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public RespBean userLogin(User user) {
        log.info(user.getUsername() + " --> 登录请求");
        if (userService.userNameExit(user.getUsername())) {
            return RespBean.error("账号不存在!");
        }
        Map<String, Object> map = loginService.login(user);
        String userId = (String) map.get("userId");
        map.remove("userId");
        User loginUser = userService.getUser(userId);
        System.out.println("User = " + loginUser);
        map.put("user", JSON.toJSON(loginUser));
        return RespBean.ok("登录成功!", map);
    }

}
