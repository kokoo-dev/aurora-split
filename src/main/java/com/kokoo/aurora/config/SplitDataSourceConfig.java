package com.kokoo.aurora.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SplitDataSourceConfig {

    private final SplitDataSourceProperties splitDataSourceProperties;

    @Bean
    public DataSource routingDataSource() {
        SplitDataSourceProperties.Write write = splitDataSourceProperties.getWrite();
        SplitDataSourceProperties.Read read = splitDataSourceProperties.getRead();

        DataSource writeDataSource = createDataSource(write.getUrl());
        DataSource readDataSource = createDataSource(read.getUrl());

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(write.getName(), writeDataSource);
        dataSourceMap.put(read.getName(), readDataSource);

        SplitRoutingDataSource splitRoutingDataSource = SplitRoutingDataSource.builder()
                .writeLookUpKey(write.getName())
                .readLookUpKey(read.getName())
                .build();
        splitRoutingDataSource.setDefaultTargetDataSource(writeDataSource); // (1)
        splitRoutingDataSource.setTargetDataSources(dataSourceMap);
        splitRoutingDataSource.afterPropertiesSet();

        return new LazyConnectionDataSourceProxy(splitRoutingDataSource);
    }

    private DataSource createDataSource(String url) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(splitDataSourceProperties.getDriverClassName());
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(splitDataSourceProperties.getUsername());
        hikariDataSource.setPassword(splitDataSourceProperties.getPassword());

        return hikariDataSource;
    }
}
