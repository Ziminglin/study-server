package com.ming.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.study.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
