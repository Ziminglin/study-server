package com.ming.study.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author: ziming
 * @Time: 2022/11/1 15:14
 */
@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha defaultKaptcha(){
        DefaultKaptcha kaptcha = new DefaultKaptcha();

        Properties properties = new Properties();
        //设置验证码的宽度
        properties.setProperty("kaptcha.image.width", "100");
        //设置宽度
        properties.setProperty("kaptcha.image.height", "40");
        //设置字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        //设置字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
        //限定验证码中的字符
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        //设置验证码的长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //设置添加噪声与否
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        //将配置装载到一个实例中
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();


        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
