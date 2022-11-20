package com.ming.study.controller.login;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.ming.study.entity.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author: ziming
 * @Time: 2022/10/5 22:38
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/kaptcha")
    public RespBean getKaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置响应头
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String text = defaultKaptcha.createText();
        HttpSession session = request.getSession();
        //将验证码存入session
        session.setAttribute("index_code", text);
        //创建验证码图片
        BufferedImage image = defaultKaptcha.createImage(text);
//        ServletOutputStream os = response.getOutputStream();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        ImageIO.write(image, "jpg", stream);
        String imgString = "data:image/jpeg;base64," + new String(Base64.encodeBase64(stream.toByteArray()));

        return RespBean.ok("获取验证码", imgString);
    }

    @GetMapping("/loginStatus")
    public RespBean loginStatus() {
        return RespBean.ok("欢迎回来!");
    }

}
