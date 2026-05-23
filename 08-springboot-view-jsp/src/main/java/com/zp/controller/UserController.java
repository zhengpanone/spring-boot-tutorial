package com.zp.controller;

import com.zp.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @RequestMapping("/showUser")
    public String showUser(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "张三", 20));
        list.add(new User(2, "李四", 20));
        //Model对象
        model.addAttribute("list", list);
        // 跳转视图
        return "userList";
    }
}
