package com.zp.neo4j.model.neo4j;

import lombok.*;

import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2025/3/4 12:42
 * Version : v1.0.0
 * Description: neo4j节点类型
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Node(labels = "Department")
public class Department {
    @Id
    //@GeneratedValue
    private String id;

    @Property("name")
    @ToString.Include
    private String name;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department parent;

    @Relationship(type = "HAS_CHILD", direction = Relationship.Direction.INCOMING)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    // 避免 NullPointerException
    private Set<Department> children= new HashSet<>();


    @Property("create_time")
    private LocalDateTime createTime;

    @Property("update_time")
    private LocalDateTime updateTime;

    public Department(String name) {
        this.name = name;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}
