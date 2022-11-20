package com.ming.study.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学院
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer collegeId;
    @TableField(exist = false)
    private String collegeName;

    /**
     * 专业名
     */
    private String majorName;

    /**
     * 班级数量
     */
    private Integer classSum;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否删除(0-未删, 1-已删)
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

    /**
     * 班主任
     */
    private String classTeacherId;
    @TableField(exist = false)
    private String classTeacherName;


}
