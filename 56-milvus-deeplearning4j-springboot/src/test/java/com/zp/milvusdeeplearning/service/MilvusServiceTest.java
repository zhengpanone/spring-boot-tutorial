package com.zp.milvusdeeplearning.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author : zhengpanone
 * Date : 2025/3/19 19:43
 * Version : v1.0.0
 * Description:
 */
@SpringBootTest
class MilvusServiceTest {

    @Resource
    MilvusService milvusService;

    @Test
    void createCollection() {
        milvusService.createCollection("image_collection","embedding", "Image collection");
    }
}