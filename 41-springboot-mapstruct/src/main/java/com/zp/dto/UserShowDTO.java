package com.zp.dto;

import lombok.Data;

@Data
public class UserShowDTO {
    private String name;

    private int sex;

    private boolean married;

    private String birthday;

    private String regDate;

    private String registerSource;

    private String favorite;

    private String memo;
}
