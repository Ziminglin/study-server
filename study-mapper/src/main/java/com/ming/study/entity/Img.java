package com.ming.study.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 系统图片
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Img implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图片类型
     */
    private Integer type;

    private String imgPath;

    /**
     * 是否删除
     */
    private Integer isDelete;


}
