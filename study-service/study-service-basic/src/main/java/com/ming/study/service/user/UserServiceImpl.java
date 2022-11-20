package com.ming.study.service.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.study.entity.SchoolInfo;
import com.ming.study.entity.User;
import com.ming.study.mapper.SchoolInfoMapper;
import com.ming.study.mapper.UserMapper;
import com.ming.study.service.schoolInfo.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private SchoolInfoMapper schoolInfoMapper;
    @Autowired
    private SchoolInfoService schoolInfoService;

    /**
     * 判断用户名是否存在
     * true:不存在
     * @param username
     * @return
     */
    @Override
    public boolean userNameExit(String username) {
        return getOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername, username)) == null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User getUser(String id) {
        User user = getById(id);
        Optional.ofNullable(user).ifPresent(this::addSchoolInfo);
        return user;
    }

    /**
     * 添加schoolInfo
     * @param user
     */
    private void addSchoolInfo(User user) {
        SchoolInfo schoolInfo = schoolInfoService.getSchoolInfo(user.getSchoolId());
        Optional.ofNullable(schoolInfo).ifPresent(user::setSchoolInfo);
    }
}
