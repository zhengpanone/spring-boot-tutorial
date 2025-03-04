package com.zp.neo4j.converter;

import com.zp.neo4j.enums.SyncStatusEnum;
import com.zp.neo4j.model.mysql.SysDept;
import com.zp.neo4j.model.neo4j.Department;
import com.zp.neo4j.repository.mysql.SysDeptRepository;
import com.zp.neo4j.repository.neo4j.DepartmentRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/5 21:04
 * Version : v1.0.0
 * Description:
 */
@Slf4j
@SpringBootTest
class SysDeptConvertMapperTest {
    private final SysDeptConvertMapper sysDeptConvertMapper = Mappers.getMapper(SysDeptConvertMapper.class);

    @Resource
    private SysDeptRepository sysDeptRepository;
    @Resource
    private DepartmentRepository departmentRepository;


    @Test
    void sysDeptToDepartment() {
        departmentRepository.deleteAll();

        Page<SysDept> all = sysDeptRepository.findBySyncStatus(SyncStatusEnum.PENDING, PageRequest.of(0, 1000));
        List<Department> departments = sysDeptConvertMapper.sysDeptListToDepartmentList(all.getContent());


    }


    @Test
    void sysDeptListToDepartmentList() {
    }
}