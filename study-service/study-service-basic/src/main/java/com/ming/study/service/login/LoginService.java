package com.ming.study.service.login;

import com.ming.study.entity.RespBean;
import com.ming.study.entity.User;

import java.util.Map;

/**
 * @author: ziming
 * @Time: 2022/10/5 22:42
 */
public interface LoginService {

    public Map<String,Object> login(User user);

    public RespBean logout();
}
