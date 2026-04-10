package com.zp.milvusdeeplearning.config;

import io.milvus.client.MilvusClient;
import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author : zhengpanone
 * Date : 2025/3/19 18:50
 * Version : v1.0.0
 * Description:
 */
@Configuration
public class MilvusConfig {

    @Value("${milvus.host:127.0.0.1}")
    private String host;
    @Value("${milvus.port:19530}")
    private int port;

    @Bean
    public MilvusClient milvusClient() {
        ConnectParam connectParam = ConnectParam.newBuilder()
                .withHost(host)
                .withPort(port)
//                .withAuthorization("root", "Milvus")
                .build();
        MilvusServiceClient milvusServiceClient = new MilvusServiceClient(connectParam);
        return milvusServiceClient;
    }
}
