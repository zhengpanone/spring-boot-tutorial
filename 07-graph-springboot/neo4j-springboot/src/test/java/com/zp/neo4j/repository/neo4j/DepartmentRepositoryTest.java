package com.zp.neo4j.repository.neo4j;


import com.zp.neo4j.model.neo4j.Department;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author : zhengpanone
 * Date : 2025/3/4 22:08
 * Version : v1.0.0
 * Description:
 */
@Slf4j
@SpringBootTest
class DepartmentRepositoryTest {

    @Resource
    private DepartmentRepository departmentRepository;

    private Department rootDept;
    private Department subDept1;
    private Department subDept2;

    //@BeforeEach
    //void setUp() {
    //    departmentRepository.deleteAll();
    //    rootDept = new Department("总公司");
    //    subDept1 = new Department("分公司1");
    //    subDept2 = new Department("分公司2");
    //
    //    // 设置子部门的父部门
    //    subDept1.setParent(rootDept);
    //    subDept2.setParent(rootDept);
    //
    //    rootDept.setChildren(Set.of(subDept1, subDept2));
    //
    //    departmentRepository.save(rootDept);
    //    departmentRepository.save(subDept1);
    //    departmentRepository.save(subDept2);
    //
    //}

    @Test
    void testFindAllDept() {
        List<Department> departments = departmentRepository.findAll();
        assertThat(departments).isNotEmpty().hasSize(3);
    }

    @Test
    void testFindDepartmentById() {
        departmentRepository.findById(rootDept.getId()).orElse(null);
        assertThat(rootDept).isNotNull();
        assertThat(rootDept.getName()).isEqualTo("总公司");
    }

    @Test
    void testFindChildrenDepartments() {
        Department parent = departmentRepository.findById(rootDept.getId()).orElse(null);
        assertThat(parent).isNotNull();
        assertThat(parent.getChildren()).isNotEmpty().hasSize(2);
    }

    //@Test
    //void testDeleteDepartment() {
    //    departmentRepository.delete(subDept1);
    //    List<Department> departments = departmentRepository.findAll();
    //    assertThat(departments).hasSize(2);
    //}

/*    @Test
    void testSaveDuplicateDepartment() {
        Department duplicate = new Department("总公司");
        assertThatThrownBy(() -> departmentRepository.save(duplicate))
                .isInstanceOf(DataAccessException.class);
    }*/

    @Test
    void testFindDepartmentTree() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Department> departments = departmentRepository.findAllDepartmentsAsTree();
        stopWatch.stop();
        log.info("findAllDepartmentsAsTree time: {}ms", stopWatch.prettyPrint());
        log.info(departments.toString());
    }
}