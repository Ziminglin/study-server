package com.ming.study.controller.course;


import com.ming.study.entity.Course;
import com.ming.study.entity.RespBean;
import com.ming.study.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: ziming
 * @Time: 2022/11/19 18:41
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getCourseList")
    public RespBean getCourseList(Course course) {
        try {
            List<Course> courseList = courseService.getCourseList(course);
            return RespBean.ok("查询成功",courseList);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("服务器错误");
        }
    }

}
