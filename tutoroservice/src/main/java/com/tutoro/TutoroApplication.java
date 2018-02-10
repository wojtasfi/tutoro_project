package com.tutoro;

import com.tutoro.dao.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableDiscoveryClient
@EnableResourceServer
@SpringBootApplication
public class TutoroApplication implements ApplicationRunner {

    @Autowired
    TutorRepository tutorRepository;

    public static void main(String[] args) {


        SpringApplication.run(TutoroApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }
}
