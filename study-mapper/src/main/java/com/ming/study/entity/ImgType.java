package com.ming.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 网站图片类型
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ImgType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String type;

    private String basePath;


}
