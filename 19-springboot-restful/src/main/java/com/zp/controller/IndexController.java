package com.zp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Author : zhengpanone
 * Date : 2025/3/18 19:58
 * Version : v1.0.0
 * Description:
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("contextPath", request.getContextPath());
        //ModelAndView modelAndView = new ModelAndView();
        return "index";
    }
}
