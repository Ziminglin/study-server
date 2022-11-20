package com.ming.study.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.study.entity.User;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
public interface UserService extends IService<User> {

    public boolean userNameExit(String username);

    public User getUser(String id);

    public boolean checkPassword(User user);

}
