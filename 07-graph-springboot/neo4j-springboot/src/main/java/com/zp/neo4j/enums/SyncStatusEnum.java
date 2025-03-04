package com.zp.neo4j.enums;

/**
 * Author : zhengpanone
 * Date : 2025/3/7 17:51
 * Version : v1.0.0
 * Description:
 */
public enum SyncStatusEnum {
    // 待同步
    PENDING("0"),
    // 正在同步
    PROCESSING("2"),
    // 已同步
    SUCCESS("1"),
    // 同步失败
    FAILED("-1");

    private final String status;

    SyncStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static SyncStatusEnum fromString(String status) {
        for (SyncStatusEnum s : SyncStatusEnum.values()) {
            if (s.status.equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("未知的状态: " + status);
    }
}

