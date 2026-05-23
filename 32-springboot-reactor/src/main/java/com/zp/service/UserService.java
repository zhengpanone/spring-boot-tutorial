package com.zp.service;

import com.zp.dao.UserRepository;
import com.zp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserService
 *
 * @author zhengpanone
 * @since 2022-09-19
 */
@Service
@Slf4j
public class UserService {
    @Resource
    private UserRepository userRepository;

    public Mono<Boolean> save(User user) {
        long startTime = System.currentTimeMillis();
        return Mono.fromSupplier(() -> {
            userRepository.save(user);
            return true;
        }).doOnError(e -> {
            // 打印异常日志&增加监控（自行处理）
            log.error("save.user.error, user={}, e", user, e);
        }).doFinally(e -> {
            // 耗时 & 整体健康
            log.info("save.user.time={}, user={}", user, System.currentTimeMillis() - startTime);

        });
    }

    public Mono<User> findById(Long id) {
        long startTime = System.currentTimeMillis();
        return Mono.fromSupplier(() -> {
            // 返回
            return userRepository.getReferenceById(id);
        }).doOnError(e -> {
            // 打印异常日志&增加监控（自行处理）
            log.error("findById.user.err, id={},e", id, e);
        }).doFinally(e -> {
            // 耗时 & 整体健康
            log.info("findById.user.time={}, id={}", id, System.currentTimeMillis() - startTime);
        });

    }

    public Mono<List<User>> list() {
        long startTime = System.currentTimeMillis();
        return Mono.fromSupplier(() -> {
            return userRepository.findAll();
        }).doOnError(e -> {
            // 打印异常日志&增加监控（自行处理）
            log.error("list.user.error, e", e);
        }).doFinally(e -> {
            // 耗时 & 整体健康
            log.info("list.user.time={}, ", System.currentTimeMillis() - startTime);
        });
    }

    public Flux<User> listFlux() {
        long startTime = System.currentTimeMillis();
        return Flux.fromIterable(userRepository.findAll())
                .doOnError(e -> {
                    // 打印异常日志&增加监控（自行处理）
                    log.error("list.user.error, e", e);
                }).doFinally(e -> {
                    // 耗时 & 整体健康
                    log.info("listFlux.user.time={}, ", System.currentTimeMillis() - startTime);
                });
    }
}
