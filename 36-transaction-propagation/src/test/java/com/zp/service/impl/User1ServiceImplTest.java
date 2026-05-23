package com.zp.service.impl;

import com.zp.pojo.User1;
import com.zp.service.User1Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class User1ServiceImplTest {
    @Autowired
    User1Service user1Service;

    @Test
    void addRequired() {
        User1 user1 = new User1();
        user1.setId(1);
        user1.setName("张三");
        user1Service.addRequired(user1);
    }
}