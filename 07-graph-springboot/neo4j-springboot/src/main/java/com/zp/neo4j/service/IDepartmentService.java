package com.zp.neo4j.service;

import com.zp.neo4j.model.neo4j.Department;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/4 12:52
 * Version : v1.0.0
 * Description:
 */
public interface IDepartmentService {
    // 获取部门树
    List<Department> getDepartmentTree(String deptId);

    // 模糊搜索部门
    List<Department> searchDepartmentTree(String keyword);

    void syncAllDepartments();
}
