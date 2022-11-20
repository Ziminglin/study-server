package com.ming.study.service.major;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.study.entity.Major;

/**
 * <p>
 * 学院 服务类
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
public interface MajorService extends IService<Major> {

    public Major getMajorById(Integer id);
}
