package com.ming.study.service.schoolInfo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.study.entity.SchoolInfo;

/**
 * <p>
 * 学校教职工表 服务类
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
public interface SchoolInfoService extends IService<SchoolInfo> {

    public SchoolInfo getSchoolInfo(String schoolId);
}
