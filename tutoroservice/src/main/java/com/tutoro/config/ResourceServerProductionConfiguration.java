package com.tutoro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
@Profile("production")
public class ResourceServerProductionConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/env", "/info", "/users/register",
                        "/users/validate/username", "/users/validate/email", "users/verify/email",
                        "/swagger-ui.html", "/swagger-ui/index.html", "/swagger-resources/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}