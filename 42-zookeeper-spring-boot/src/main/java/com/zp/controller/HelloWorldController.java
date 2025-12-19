package com.zp.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author : zhengpanone
 * Date : 2023/10/23 14:21
 * Version : v1.0.0
 * Description: TODO
 */
public class HelloWorldController {
    @GetMapping("/helloworld")
    public String HelloWorld() {
        return "Hello World!";
    }
}
