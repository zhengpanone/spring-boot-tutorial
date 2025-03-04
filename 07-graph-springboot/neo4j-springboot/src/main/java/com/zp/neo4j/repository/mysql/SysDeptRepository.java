package com.zp.neo4j.repository.mysql;

import com.zp.neo4j.enums.SyncStatusEnum;
import com.zp.neo4j.model.mysql.SysDept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/5 19:53
 * Version : v1.0.0
 * Description:
 */
@Repository
public interface SysDeptRepository extends JpaRepository<SysDept, String> {

    // 根据 syncStatus 查找同步的部门（分页）
    Page<SysDept> findBySyncStatus(SyncStatusEnum syncStatus, Pageable pageable);

    List<SysDept> findByIdIn(Collection<String> ids);

    // 根据 id 更新 syncStatus
    @Transactional
    @Modifying
    @Query("UPDATE SysDept d SET d.syncStatus = :syncStatus WHERE d.id IN :ids")
    int updateSyncStatusByIds(@Param("syncStatus") SyncStatusEnum syncStatus, @Param("ids") List<String> ids);


}
