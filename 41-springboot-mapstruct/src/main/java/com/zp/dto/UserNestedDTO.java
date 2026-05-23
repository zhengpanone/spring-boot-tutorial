package com.zp.dto;

import lombok.Data;

@Data
public class UserNestedDTO {
    private String name;

    private int sex;

    private boolean married;

    private String birthday;

    private String regDate;

    private String regSource;

    private String favorite;

    private String school;

    private String city;

    private String address;

    private String memo;
}
