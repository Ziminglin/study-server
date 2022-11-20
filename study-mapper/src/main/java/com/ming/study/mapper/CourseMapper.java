package com.ming.study.mapper;

import com.ming.study.entity.Course;
import icu.mhb.mybatisplus.plugln.base.mapper.JoinBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程表 Mapper 接口
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
@Mapper
public interface CourseMapper extends JoinBaseMapper<Course> {

}
