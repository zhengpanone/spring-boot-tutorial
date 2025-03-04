package com.zp.neo4j.model.mysql;

import com.zp.neo4j.enums.SyncStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;


/**
 * Author : zhengpanone
 * Date : 2025/3/5 19:47
 * Version : v1.0.0
 * Description:
 */

@Entity
@Table(name = "sys_dept")
@Data
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysDept {
    @Id
    @Column(length = 32, nullable = false)
    @Comment("部门id")
    private String id;

    @Column(nullable = false, length = 255)
    @Comment("部门名称")
    private String name = "";

    @Column(nullable = false, length = 32)
    @Comment("父部门id")
    private String parentId;

    @Column(nullable = false)
    @Comment("显示顺序")
    private Integer sort = 0;

    @Comment("负责人")
    private Long leaderUserId;

    @Column(length = 11)
    @Comment("联系电话")
    private String phone;

    @Column(length = 50)
    @Comment("邮箱")
    private String email;

    @Column(nullable = false)
    @Comment("部门状态（0正常 1停用）")
    private Byte status;

    @Column(length = 64)
    @Comment("创建者")
    private String creator = "";

    @Column(nullable = false, updatable = false)
    @Comment("创建时间")
    private LocalDateTime createTime = LocalDateTime.now();

    @Column(length = 64)
    @Comment("更新者")
    private String updater = "";

    @Column(nullable = false)
    @Comment("更新时间")
    private LocalDateTime updateTime = LocalDateTime.now();

    @Column(nullable = false)
    @Comment("是否删除")
    private Boolean deleted = false;

    @Column(nullable = false)
    @Comment("租户编号")
    private Long tenantId = 0L;

    @Enumerated(EnumType.ORDINAL)  // 将枚举类型映射为字符串
    @Column(name = "sync_status", nullable = false)
    @Comment("同步状态")
    private SyncStatusEnum syncStatus;
}

