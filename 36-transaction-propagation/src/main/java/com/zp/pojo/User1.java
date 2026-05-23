package com.zp.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User1  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
}
