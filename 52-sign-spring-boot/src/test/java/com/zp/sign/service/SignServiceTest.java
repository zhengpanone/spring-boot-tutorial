package com.zp.sign.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class SignServiceTest {

    @Autowired
    private SignService signService;

    @Test
    public void testSignFeatures() {
        String userId = "1001";
        LocalDate today = LocalDate.now();

        System.out.println("签到结果: " + signService.sign(userId, today));
        System.out.println("检查签到: " + signService.checkSign(userId, today));
        System.out.println("本月签到次数: " + signService.getSignCount(userId, today));
        System.out.println("连续签到天数: " + signService.getContinuousSignCount(userId, today));
        List<Boolean> info = signService.getSignInfo(userId, today);
        System.out.println("本月签到详情: " + info);
    }
}