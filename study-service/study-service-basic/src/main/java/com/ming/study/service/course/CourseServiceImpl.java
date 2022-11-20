package com.ming.study.service.course;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.study.entity.Course;
import com.ming.study.entity.SchoolInfo;
import com.ming.study.mapper.CourseMapper;
import com.ming.study.mapper.SchoolInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private SchoolInfoMapper schoolInfoMapper;

    @Override
    public List<Course> getCourseList(Course course) {
        LambdaQueryWrapper<Course> qw = new LambdaQueryWrapper<>();
        qw.eq(course.getMajorId() != null, Course::getMajorId, course.getMajorId())
                .eq(course.getTeacherId() != null, Course::getTeacherId, course.getTeacherId());
        List<Course> courses = courseMapper.selectList(qw);
        addTeacherName(courses);
        return courses;
    }

    private void addTeacherName(List<Course> courses) {
        Set<String> teacherIds = courses.stream().map(Course::getTeacherId).collect(Collectors.toSet());
        List<SchoolInfo> schoolInfos = schoolInfoMapper.selectList(Wrappers.lambdaQuery(SchoolInfo.class).in(SchoolInfo::getId, teacherIds));
        Map<String, String> hashMap = schoolInfos.stream().collect(Collectors.toMap(SchoolInfo::getId, SchoolInfo::getRealName));
        courses.forEach(c -> c.setTeacherName(hashMap.get(c.getTeacherId())));

    }
}
