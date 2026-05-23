package com.zp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExtDO {
    private String regSource;

    private String favorite;

    private String school;

    private int kids;

    private String memo;
}
