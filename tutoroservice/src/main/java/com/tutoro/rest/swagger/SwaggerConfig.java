package com.tutoro.rest.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket home() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("home rest")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tutoro.rest"))
                .paths(PathSelectors.ant("/home/*"))
                .build();
    }

    @Bean
    public Docket learnRelations() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("learn relations rest")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tutoro.rest"))
                .paths(PathSelectors.ant("/learn_relations/*"))
                .build();
    }

    @Bean
    public Docket teachers() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("teachers rest")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tutoro.rest"))
                .paths(PathSelectors.ant("/teachers/*"))
                .build();
    }

    @Bean
    public Docket students() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("students rest")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tutoro.rest"))
                .paths(PathSelectors.ant("/students/*"))
                .build();
    }

    @Bean
    public Docket pictures() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("pictures rest")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tutoro.rest"))
                .paths(PathSelectors.ant("/pictures/*"))
                .build();
    }

    @Bean
    public Docket search() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("search rest")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tutoro.rest"))
                .paths(PathSelectors.ant("/search/*"))
                .build();
    }

    @Bean
    public Docket skills() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("skills rest")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tutoro.rest"))
                .paths(PathSelectors.ant("/skills/*"))
                .build();
    }

    @Bean
    public Docket tutors() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("tutors rest")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tutoro.rest"))
                .paths(PathSelectors.ant("/tutors/*"))
                .build();
    }
}

