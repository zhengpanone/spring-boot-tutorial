package com.zp.pojo;

import lombok.Data;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2025/3/3 19:45
 * Version : v1.0.0
 * Description:
 */
@Data
public class Order {
    private Integer id;
    private String amount;

    private String status;

    private List<OrderDetail> items;
}
