package com.ming.study.service.course;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.study.entity.Course;

import java.util.List;

/**
 * <p>
 * 课程表 服务类
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
public interface CourseService extends IService<Course> {

    public List<Course> getCourseList(Course course);

}
