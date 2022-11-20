package com.ming.study.controller.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ziming
 * @Time: 2022/10/5 21:17
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('dev:code:pull')")
//    @PreAuthorize("@ex.hasAuthority('dev:code:pull')")
    public String hello(){
        return "hello world";
    }

    // 每一个接口方法上写死权限
}
