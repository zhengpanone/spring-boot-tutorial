package com.zp.neo4j.controller;

import com.zp.neo4j.model.neo4j.Department;
import com.zp.neo4j.service.IDepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/4 12:55
 * Version : v1.0.0
 * Description:
 */
@RestController
@RequestMapping("/dept")
public class DepartmentController {

    private final IDepartmentService departmentService;

    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/tree/{deptId}")
    public List<Department> getDepartmentTree(@PathVariable("deptId") String deptId) {
        return departmentService.getDepartmentTree(deptId);
    }

    @GetMapping("/search")
    public List<Department> searchDepartmentTree(@RequestParam("keyword") String keyword) {
        return departmentService.searchDepartmentTree(keyword);
    }


}
