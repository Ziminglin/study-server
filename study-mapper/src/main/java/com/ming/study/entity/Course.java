package com.ming.study.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
@Data
@NoArgsConstructor
@TableName("course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专业id
     */
    private Integer majorId;

    /**
     * 讲师id
     */
    private String teacherId;

    @TableField(exist = false)
    public String teacherName;

    /**
     * 课程名
     */
    private String title;

    /**
     * 封面路径
     */
    private String cover;

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

    private String description;
}
