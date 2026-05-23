package com.zp.dto;

import lombok.Data;

@Data
public class UserCustomDTO {
    private String name;

    private int sex;

    private boolean married;

    private String birthday;

    private String regDate;

    private UserExtDTO userExtDTO;

    private String memo;
}
