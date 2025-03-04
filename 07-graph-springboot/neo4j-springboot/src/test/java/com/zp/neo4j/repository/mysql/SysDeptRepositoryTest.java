//package com.zp.neo4j.repository.mysql;
//
//import com.zp.neo4j.enums.SyncStatusEnum;
//import com.zp.neo4j.model.mysql.SysDept;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Author : zhengpanone
// * Date : 2025/3/7 18:00
// * Version : v1.0.0
// * Description:
// */
//@Slf4j
//@SpringBootTest
//class SysDeptRepositoryTest {
//    @Resource
//    private SysDeptRepository sysDeptRepository;
//
//    @Test
//    void findBySyncStatus() {
//        Page<SysDept> bySyncStatus = sysDeptRepository.findBySyncStatus(SyncStatusEnum.PENDING, PageRequest.of(0, 10));
//        log.debug(bySyncStatus.toString());
//    }
//}