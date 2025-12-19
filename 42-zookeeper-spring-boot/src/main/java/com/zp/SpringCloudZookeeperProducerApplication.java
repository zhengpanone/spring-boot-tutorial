package com.zp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author : zhengpanone
 * Date : 2023/10/23 14:08
 * Version : v1.0.0
 * Description: TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudZookeeperProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZookeeperProducerApplication.class,args);
        System.out.println("Hello world!");
    }
}