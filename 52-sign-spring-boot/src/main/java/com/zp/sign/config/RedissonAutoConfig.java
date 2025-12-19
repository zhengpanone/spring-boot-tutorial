package com.zp.sign.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Data
//@Configuration
//@ConfigurationProperties(prefix = "redisson")
public class RedissonAutoConfig {

    // 单机、哨兵、集群模式配置
    private SingleServerConfig single;
    private SentinelServersConfig sentinel;
    private ClusterServersConfig cluster;
    private MasterSlaveServersConfig masterSlave;

    // 通用配置
    private int threads = 16;
    private int nettyThreads = 32;
    private String codec = "org.redisson.codec.JsonJacksonCodec";

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public RedissonClient redissonClient() {
        Config config = new Config();

        // 根据配置选择模式
        if (single != null && single.getAddress() != null) {
            // 单机模式
            SingleServerConfig serverConfig = config.useSingleServer();
            applySingleServerConfig(serverConfig, single);
        } else if (sentinel != null && sentinel.getSentinelAddresses() != null) {
            // 哨兵模式
            SentinelServersConfig sentinelConfig = config.useSentinelServers();
            applySentinelConfig(sentinelConfig, sentinel);
        } else if (cluster != null && cluster.getNodeAddresses() != null) {
            // 集群模式
            ClusterServersConfig clusterConfig = config.useClusterServers();
            applyClusterConfig(clusterConfig, cluster);
        } else {
            throw new IllegalArgumentException("Redisson配置错误：未指定有效的服务器模式");
        }

        // 通用配置
        config.setThreads(threads);
        config.setNettyThreads(nettyThreads);
        config.setCodec(new org.redisson.codec.JsonJacksonCodec());

        return Redisson.create(config);
    }
    // 单机模式
    private void applySingleServerConfig(SingleServerConfig config, SingleServerConfig props) {
        config.setAddress(props.getAddress())
                .setPassword(props.getPassword())
                .setDatabase(props.getDatabase())
                .setConnectionPoolSize(props.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(props.getConnectionMinimumIdleSize())
                .setTimeout(props.getTimeout());
    }
    // 哨兵模式
    private void applySentinelConfig(SentinelServersConfig config, SentinelServersConfig props) {
        config.setMasterName(props.getMasterName())
                .addSentinelAddress(props.getSentinelAddresses().toArray(new String[0]))
                .setPassword(props.getPassword())
                .setTimeout(props.getTimeout());

    }

    // 集群模式
    private void applyClusterConfig(ClusterServersConfig config, ClusterServersConfig props) {

    }


}