package com.zp.neo4j.repository.neo4j;

import com.zp.neo4j.model.neo4j.Department;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/4 12:46
 * Version : v1.0.0
 * Description:
 */
@Repository
public interface DepartmentRepository extends Neo4jRepository<Department, String> {

    @Query("MATCH (n:Department) DETACH DELETE n")
    void deleteAll();

    // 查询某个部门及其所有子部门
    @Query("MATCH (d:Department {elementId: $deptId})-[:BELONGS_TO*0..]->(sub) RETURN sub")
    List<Department> findDepartmentTree(@Param("deptId") String deptId);

    // 模糊搜索部门，并返回其完整树结构
    @Query("MATCH (d:Department) WHERE d.name CONTAINS $keyword " +
            "MATCH (d)-[:BELONGS_TO*0...]->(sub) RETURN sub")
    List<Department> searchDepartmentTree(String keyword);

    /**
     * 查询所有部门，以树形结构返回
     */
    @Query(" MATCH (d:Department) OPTIONAL MATCH (d)<-[:HAS_CHILD]-(child:Department) RETURN d, COLLECT(child) AS children")
    List<Department> findAllDepartmentsAsTree();
}
