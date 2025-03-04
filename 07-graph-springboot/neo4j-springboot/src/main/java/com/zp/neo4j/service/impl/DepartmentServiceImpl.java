package com.zp.neo4j.service.impl;

//import com.zp.neo4j.model.mysql.SysDept;

import com.zp.neo4j.converter.SysDeptConvertMapper;
import com.zp.neo4j.enums.SyncStatusEnum;
import com.zp.neo4j.model.mysql.SysDept;
import com.zp.neo4j.model.neo4j.Department;
//import com.zp.neo4j.repository.mysql.SysDeptRepository;
import com.zp.neo4j.repository.mysql.SysDeptRepository;
import com.zp.neo4j.repository.neo4j.DepartmentRepository;
import com.zp.neo4j.service.IDepartmentService;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author : zhengpanone
 * Date : 2025/3/4 12:52
 * Version : v1.0.0
 * Description:
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {

    private final DepartmentRepository departmentRepository;

    private final SysDeptRepository sysDeptRepository;

    private final SysDeptConvertMapper sysDeptConvertMapper = Mappers.getMapper(SysDeptConvertMapper.class);

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, SysDeptRepository sysDeptRepository) {
        this.departmentRepository = departmentRepository;
        this.sysDeptRepository = sysDeptRepository;
    }

    @Override
    public void syncAllDepartments() {
        departmentRepository.deleteAll();
        while (true) {
            Page<SysDept> page = sysDeptRepository.findBySyncStatus(SyncStatusEnum.PENDING, PageRequest.of(0, 1000));
            if (page.isEmpty()) {
                break;
            }
            List<SysDept> content = page.getContent();
            Set<String> partentIdSet = content.stream().map(SysDept::getParentId).collect(Collectors.toSet());
            List<SysDept> parentList = sysDeptRepository.findByIdIn(partentIdSet);
            parentList.addAll(content);

            List<Department> departments = sysDeptConvertMapper.sysDeptListToDepartmentList(parentList);
            if (CollectionUtils.isEmpty(departments)) {
                break;
            }
            processBatch(departments, parentList);
        }

    }

    @Transactional
    public void processBatch(List<Department> departments, List<SysDept> sysDeptList) {
        departmentRepository.saveAll(departments); // 保存转换后的 Department 数据
        // 更新状态
        sysDeptRepository.updateSyncStatusByIds(SyncStatusEnum.SUCCESS,
                sysDeptList.stream().map(SysDept::getId).collect(Collectors.toList()));
    }


    @Override
    public List<Department> getDepartmentTree(String deptId) {
        return departmentRepository.findDepartmentTree(deptId);
    }

    @Override
    public List<Department> searchDepartmentTree(String keyword) {
        return departmentRepository.searchDepartmentTree(keyword);
    }
}
