package com.ming.study.service.schoolInfo;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.study.entity.Major;
import com.ming.study.entity.SchoolInfo;
import com.ming.study.entity.UserRole;
import com.ming.study.mapper.SchoolInfoMapper;
import com.ming.study.service.major.MajorService;
import com.ming.study.service.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 学校教职工表 服务实现类
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
@Service
public class SchoolInfoServiceImpl extends ServiceImpl<SchoolInfoMapper, SchoolInfo> implements SchoolInfoService {

    @Autowired
    private MajorService majorService;
    @Autowired
    private UserRoleService roleService;


    /**
     * @param schoolId
     * @return
     */
    @Override
    public SchoolInfo getSchoolInfo(String schoolId) {
        SchoolInfo schoolInfo = getById(schoolId);
        Optional.ofNullable(schoolInfo).ifPresent(this::addInfo);
        return schoolInfo;
    }

    /**
     * 联表查询
     *
     * @param schoolInfo
     */
    private void addInfo(SchoolInfo schoolInfo) {
        Major major = majorService.getMajorById(schoolInfo.getMajorId());
        UserRole role = roleService.getById(schoolInfo.getRoleId());

        Optional.ofNullable(major).ifPresent(m -> {
            schoolInfo.setMajorName(m.getMajorName());
            schoolInfo.setCollegeName(m.getCollegeName());
        });
        Optional.ofNullable(role).ifPresent(r -> schoolInfo.setRoleName(r.getRoleName()));

    }
}
