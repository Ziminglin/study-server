package com.ming.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: ziming
 * @Time: 2022/11/2 14:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest implements Serializable {
    /**
     * 接收人
     */
    private String sendTo;

    /**
     *  邮件主题
     */
    private String subject;

    /**
     *  邮件内容
     */
    private String text;

}
