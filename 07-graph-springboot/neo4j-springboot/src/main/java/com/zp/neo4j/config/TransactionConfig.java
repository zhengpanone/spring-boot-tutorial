package com.zp.neo4j.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionConfig {

    @Bean
    public PlatformTransactionManager transactionManager(
            @Qualifier("mysqlTransactionManager") PlatformTransactionManager mysqlTransactionManager,
            @Qualifier("neo4jTransactionManager") PlatformTransactionManager neo4jTransactionManager) {
        
        return new ChainedTransactionManager(mysqlTransactionManager, neo4jTransactionManager);
    }
}
