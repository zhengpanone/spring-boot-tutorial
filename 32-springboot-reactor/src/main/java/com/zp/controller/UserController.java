package com.zp.controller;

import com.zp.entity.User;
import com.zp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserController
 *
 * @author zhengpanone
 * @since 2022-09-19
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/add")
    public Mono<Boolean> add() {
        User user = new User("张三", 10, "F");
        return userService.save(user);
    }

    @GetMapping("/list")
    public Mono<List<User>> list() {
        return userService.list();
    }

    @GetMapping("/listFlux")
    public Flux<User> listFlux() {
        return userService.listFlux();
    }
}
