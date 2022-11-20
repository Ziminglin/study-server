package com.ming.study.controller.email;

import com.ming.study.entity.MailRequest;
import com.ming.study.entity.RespBean;
import com.ming.study.service.email.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: ziming
 * @Time: 2022/11/2 15:04
 */
@RestController
public class EmailController {

    @Autowired
    private SendMailService sendMailService;

    @GetMapping("/sentCode")
    public RespBean sentCode(String email, HttpServletRequest request) {
        return sent(email, request);
    }

    @GetMapping("/updateEmailCode")
    public RespBean updateEmailCode(String email, HttpServletRequest request) {
        return sent(email, request);
    }

    public RespBean sent(String email, HttpServletRequest request) {
        String code_num = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        MailRequest mail = new MailRequest(email, "系统验证码", code_num);
        HttpSession session = request.getSession();
        //将验证码存入session
        session.setAttribute("email_code", code_num);
        try {
            sendMailService.sendSimpleMail(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("发送验证码失败！");
        }

        return RespBean.ok("发送验证码成功！");
    }
}
