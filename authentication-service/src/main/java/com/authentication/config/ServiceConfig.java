package com.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ServiceConfig {
  @Value("${signing.key}")
  private String jwtSigningKey="";


    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.database.driverClassName}")
    private String dbDriverClassName;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

  public String getJwtSigningKey() {
    return jwtSigningKey;
  }

    public String getDatasourceUrl() {
        return datasourceUrl;
    }

    public String getDbDriverClassName() {
        return dbDriverClassName;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
