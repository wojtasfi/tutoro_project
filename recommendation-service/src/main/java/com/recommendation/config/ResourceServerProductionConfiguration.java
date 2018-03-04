package com.recommendation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
@Profile("production")
public class ResourceServerProductionConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/env", "/info", "/swagger-resources/**", "/swagger-ui/index.html")
                .permitAll()
                .antMatchers("/recommendation/**")
                .hasRole("TUTOR")
                .anyRequest()
                .authenticated();
    }
}