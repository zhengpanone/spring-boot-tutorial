package com.zp.entity;

import lombok.Data;

@Data
public class UserAddressDO {
    private String province;

    private String city;

    private String postcode;

    private String address;

    private String memo;
}
