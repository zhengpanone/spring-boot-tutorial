package com.zp.controller;

import com.zp.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * UserController
 *
 * @author zhengpanone
 * @since 2022-01-05
 */
@RestController
@RequestMapping("/users")
public class UserController {
    // 创建线程安全的Map,模拟users信息存储
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @GetMapping("/")
    public List<User> getUserList() {
        return new ArrayList<>(users.values());
    }

    @PostMapping("/")
    public String addUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
