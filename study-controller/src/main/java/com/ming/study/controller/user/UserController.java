package com.ming.study.controller.user;

import com.alibaba.fastjson.JSON;
import com.ming.study.entity.RespBean;
import com.ming.study.entity.SchoolInfo;
import com.ming.study.entity.User;
import com.ming.study.service.login.LoginService;
import com.ming.study.service.schoolInfo.SchoolInfoService;
import com.ming.study.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

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
    @Autowired
    private SchoolInfoService schoolInfoService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 登录
     *
     * @param user
     * @return RespBean
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

    /**
     * 用户登出
     *
     * @return RespBean
     */
    @PostMapping("/logout")
    public RespBean logout() {
        return loginService.logout();
    }

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return RespBean
     */
    @GetMapping("/queryUsernameExit")
    public RespBean queryUsernameExit(String username) {
        boolean flag = userService.userNameExit(username);
        return RespBean.ok(flag);
    }

    /**
     * 新用户注册
     *
     * @param user
     * @param code
     * @param request
     * @return RespBean
     */
    @PostMapping("/insertUser")
    public RespBean insertUser(User user, String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String emailCode = (String) session.getAttribute("email_code");
        try {
            if (!emailCode.equals(code)) {
                return RespBean.error("验证码错误！");
            }
        } catch (Exception e) {
            return RespBean.error("请先发送验证码！");
        }
        try {
            UUID uuid = UUID.randomUUID();
            user.setId(uuid.toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
        } catch (Exception e) {
            log.error("新增用户失败：" + e.getMessage());
            return RespBean.error("添加失败");
        }
        log.info("新增用户成功:" + user.getUsername());
        return RespBean.ok("添加成功");
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return RespBean
     */
    @PostMapping("/updateUserInfo")
    public RespBean updateUserInfo(@RequestBody User user) {
        if (user.getPassword() != null && !userService.checkPassword(user)) {
            return RespBean.error("密码错误!");
        }
        try {
            userService.updateById(user);
        } catch (Exception e) {
            log.error("修改失败:" + e.getMessage());
            return RespBean.ok("修改失败!");
        }
        log.info("修改信息成功");
        return RespBean.ok("修改成功!");
    }

    /**
     * 修改邮箱
     *
     * @param user
     * @param code
     * @param request
     * @return RespBean
     */
    @PostMapping("/updateEmail")
    public RespBean updateEmail(User user, String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String emailCode = (String) session.getAttribute("email_code");
        try {
            if (!emailCode.equals(code)) {
                return RespBean.error("验证码错误！");
            }
        } catch (Exception e) {
            return RespBean.error("请先发送验证码！");
        }
        try {
            userService.updateById(user);
        } catch (Exception e) {
            log.error("修改失败:" + e.getMessage());
            return RespBean.ok("修改失败!");
        }
        log.info("修改邮箱成功");
        return RespBean.ok("修改成功!");
    }

    /**
     * 查询用户认证信息
     *
     * @param schoolId
     * @return
     */
    @PostMapping("/getSchoolInfoBySchoolId")
    public RespBean getSchoolInfoBySchoolId(String schoolId) {

        try {
            SchoolInfo schoolInfo = schoolInfoService.getSchoolInfo(schoolId);
            if (schoolInfo != null) {
                return RespBean.ok("查询成功", schoolInfo);
            } else {
                return RespBean.error("schoolId不存在");
            }
        } catch (Exception e) {
            log.error("服务器错误:" + e.getMessage());
            return RespBean.error("服务器错误");
        }
    }

    /**
     * 注销用户
     * @param userId
     * @return RespBean
     */
    @DeleteMapping("/delUser")
    public RespBean delUser(String userId) {
        try {
            userService.removeById(userId);
        } catch (Exception e) {
            log.error("注销失败:" + e.getMessage());
            return RespBean.error("注销失败");
        }
        log.info("用户注销,userId:" + userId);
        return RespBean.ok("注销成功");
    }
}
