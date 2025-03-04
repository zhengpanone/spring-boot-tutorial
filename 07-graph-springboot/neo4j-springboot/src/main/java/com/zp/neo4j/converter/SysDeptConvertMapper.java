package com.zp.neo4j.converter;

import com.zp.neo4j.model.mysql.SysDept;
import com.zp.neo4j.model.neo4j.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author : zhengpanone
 * Date : 2025/3/5 20:04
 * Version : v1.0.0
 * Description:
 */
@Mapper(componentModel = "spring")
public interface SysDeptConvertMapper {

    //SysDeptConvertMapper INSTANCE = Mappers.getMapper(SysDeptConvertMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "updateTime", target = "updateTime")
    @Mapping(target = "parent", ignore = true) // 父部门关系手动处理
    @Mapping(target = "children", ignore = true)
        // 子部门关系手动处理
    Department sysDeptToDepartment(SysDept sysDept);

    default List<Department> sysDeptListToDepartmentList(List<SysDept> sysDepts) {
        if (CollectionUtils.isEmpty(sysDepts)) {
            return Collections.emptyList();
        }
        // 1. 先转换基本字段
        Map<String, Department> deptMap = sysDepts.stream().collect(Collectors.toMap(SysDept::getId, this::sysDeptToDepartment, (key1, key2) -> key1));

        // 2. 构建父子关系
        sysDepts.forEach(sysDept ->
                Optional.ofNullable(deptMap.get(sysDept.getId())).ifPresent(dept -> {
                    Optional.ofNullable(sysDept.getParentId())
                            .map(deptMap::get)
                            .ifPresent(parentDept -> {
                                dept.setParent(parentDept);
                                parentDept.getChildren().add(dept);
                            });
                })
        );
        // 3. 返回根部门列表
        return deptMap.values().stream()
                // 只返回顶级部门
                .filter(dept -> dept.getParent() == null)
                .collect(Collectors.toList());

    }
}
