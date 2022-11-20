package com.ming.study.test;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ming.study.entity.Course;
import com.ming.study.entity.Major;
import com.ming.study.entity.User;
import com.ming.study.service.course.CourseService;
import com.ming.study.service.major.MajorService;
import com.ming.study.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: ziming
 * @Time: 2022/11/20 3:23
 */
@SpringBootTest
public class CourseTest {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    MajorService majorService;

    @Test
    public void testQuery(){
        Course course = new Course();
        course.setMajorId(1);
        List<Course> courseList = courseService.getCourseList(course);
        System.out.println("courseList = " + courseList);
    }

    @Test
    public void testQueryName(){
        User user = new User();
        user.setUsername("zhangsan");
        if (userService.getOne(Wrappers.lambdaQuery(User.class).eq(User::getUsername,user.getUsername()))==null) {
            System.out.println("账号不存在");
        }
    }

    @Test
    public void testMajor(){
        Major byId = majorService.getById(1);
        System.out.println("byId = " + byId);

    }
}
