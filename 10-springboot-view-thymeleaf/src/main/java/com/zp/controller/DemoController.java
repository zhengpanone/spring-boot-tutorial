package com.zp.controller;

import com.zp.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class DemoController {
    @RequestMapping("/showInfo")
    public String showInfo(Model model) {
        model.addAttribute("msg", "Thymeleaf案例");
        model.addAttribute("date", new Date());
        model.addAttribute("sex", "男");
        model.addAttribute("id", 3);
        List<User> list = new ArrayList<>();
        list.add(new User(1, "张三", 20));
        list.add(new User(2, "李四", 20));
        model.addAttribute("user", list);

        Map<String, User> map = new HashMap<>();
        map.put("u1", new User(1, "张三", 20));
        map.put("u2", new User(2, "李四", 21));
        map.put("u3", new User(3, "王五", 22));

        model.addAttribute("map", map);
        return "index";
    }

    @RequestMapping("/showInfo2")
    public String showInfo2(HttpServletRequest request, Model model) {
        System.out.println("show2");
        request.setAttribute("req", "HttpServletRequest");
        request.getSession().setAttribute("sess", "HttpSession");
        request.getSession().getServletContext().setAttribute("app", "Application");
        return "index2";
    }

    @RequestMapping("/{page}")
    public String showInfo3(@PathVariable String page, Integer id) {
        System.out.println("show3" + id);
        return page;
    }


}
