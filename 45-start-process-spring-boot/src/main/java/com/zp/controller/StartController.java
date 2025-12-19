package com.zp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhengpanone
 * Date : 2023/10/30 22:28
 * Version : v1.0.0
 * Description: TODO
 */
@RestController
@RequestMapping("/start")
public class StartController {
    @GetMapping("/get")
    public String startSpringBoot(){
        return "Start Spring Boot";
    }
}
