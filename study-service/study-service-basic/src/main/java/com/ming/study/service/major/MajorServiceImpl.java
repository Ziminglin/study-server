package com.ming.study.service.major;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.study.entity.College;
import com.ming.study.entity.Major;
import com.ming.study.mapper.MajorMapper;
import com.ming.study.service.college.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 学院 服务实现类
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {

    @Autowired
    private CollegeService collegeService;

    /**
     * @param id
     * @return
     */
    @Override
    public Major getMajorById(Integer id) {
//        LambdaQueryWrapper<Major> wrapper = Wrappers.lambdaQuery(Major.class).eq(Major::getCollegeId, id);
        Major major = getById(id);
        Optional.ofNullable(major).ifPresent(this::addCollegeName);
        return major;
    }

    private void addCollegeName(Major major) {
        College college = collegeService.getById(major.getCollegeId());
        Optional.ofNullable(college).ifPresent(c->major.setCollegeName(c.getCollegeName()));
    }
}
