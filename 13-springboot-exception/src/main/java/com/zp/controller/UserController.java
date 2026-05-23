package com.zp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * SpringBoot处理异常方式一、自定义错误页面
 */
@Controller
public class UserController {

   @RequestMapping("/index")
    public String index() {
       System.out.println("热部署");
        return "index";
    }

    @ExceptionHandler(value = {java.lang.NullPointerException.class})
    public ModelAndView nullPointerExceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("error", e.toString());
        mv.setViewName("error2");
        return mv;
    }
}
