package com.zp.controller;

import com.zp.pojo.User;
import com.zp.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserSerivce userSerivce;

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    @RequestMapping("/addUser")
    public String addUser(User user) {
        this.userSerivce.addUser(user);
        return "ok";
    }

    @RequestMapping("/findUserAll")
    public String findUserAll(Model model) {
        List<User> allUser = this.userSerivce.getAllUser();
        model.addAttribute("list", allUser);

        return "show_all_user";
    }

    @RequestMapping("/findUserById")
    public String findUserById(Integer id, Model model) {
        User user = this.userSerivce.findUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user) {
        this.userSerivce.updateUser(user);
        return "ok";
    }

    @RequestMapping("/deleteUserById")
    public String deleteUserById(Integer id) {
        this.userSerivce.deleteUserById(id);
        return "redirect:/user/findUserAll";
    }
}
