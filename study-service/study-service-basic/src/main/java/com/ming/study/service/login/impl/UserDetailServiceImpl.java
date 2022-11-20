package com.ming.study.service.login.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ming.study.entity.User;
import com.ming.study.mapper.UserMapper;
import com.ming.study.utils.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: ziming
 * @Time: 2022/10/5 21:51
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

//    @Autowired
//    MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.根据用户名获取数据库用户数据
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,username);  // 查询条件
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (Objects.isNull(user)){
            throw new RuntimeException("账号不存在!");
        }

        // 2. TODO 权限信息
//        List<String> list = new ArrayList<>(Arrays.asList("sayHello","delGoods"));
//        List<String> perms = menuMapper.selectPermsByUserId(user.getId());

        // 3.返回UserDetails
        return new LoginUser(user,null);
    }
}
