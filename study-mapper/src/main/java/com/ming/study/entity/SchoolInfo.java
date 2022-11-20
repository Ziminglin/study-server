package com.ming.study.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 学校教职工表
 * </p>
 *
 * @author ziming
 * @since 2022-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SchoolInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学校id
     */
    private String id;

    /**
     * 人名
     */
    private String realName;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 专业id
     */
    private Integer majorId;
    @TableField(exist = false)
    private String majorName;

    @TableField(exist = false)
    private String collegeName;

    /**
     * 性别(0:男,1:女)
     */
    private String sex;

    private Integer roleId;
    @TableField(exist = false)
    private String roleName;

    /**
     * 入学时间
     */
    @TableField("Admission_date")
    private String admissionDate;

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


}
