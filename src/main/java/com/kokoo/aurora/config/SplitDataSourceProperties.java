package com.kokoo.aurora.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mysql")
public class SplitDataSourceProperties {

    private String driverClassName;
    private String username;
    private String password;
    private Write write;
    private Read read;

    @Getter
    @Setter
    public static class Write {
        private String name;
        private String url;
    }

    @Getter
    @Setter
    public static class Read {
        private String name;
        private String url;
    }
}
