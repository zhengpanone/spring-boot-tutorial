package com.zp.neo4j.service.impl;

import com.zp.neo4j.converter.SysDeptConvertMapper;
import com.zp.neo4j.service.IDepartmentService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author : zhengpanone
 * Date : 2025/3/8 11:14
 * Version : v1.0.0
 * Description:
 */
@SpringBootTest
class DepartmentServiceImplTest {
    @Resource
    private IDepartmentService departmentService;

    @Test
    void syncAllDepartments() {
        departmentService.syncAllDepartments();
    }

    @Test
    void processBatch() {
    }

    @Test
    void getDepartmentTree() {
    }

    @Test
    void searchDepartmentTree() {
    }
}