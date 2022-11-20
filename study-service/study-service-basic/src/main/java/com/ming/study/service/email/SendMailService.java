package com.ming.study.service.email;

import com.ming.study.entity.MailRequest;

/**
 * @author: ziming
 * @Time: 2022/11/2 15:00
 */
public interface SendMailService {
    /**
     * 简单文本邮件
     *
     * @param mailRequest
     * @return
     */
    void sendSimpleMail(MailRequest mailRequest);
}
