package com.crs.microservices.guestprofileservice.configuration;

import com.crs.microservices.guestprofileservice.mapper.Mapper;
import com.crs.microservices.guestprofileservice.service.GuestService;
import com.crs.microservices.guestprofileservice.mapper.MapperImpl;
import com.crs.microservices.guestprofileservice.service.GuestServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class GuestConfiguration {
    @Bean
    public GuestService getGuestService() {
        return new GuestServiceImpl();
    }

    @Bean
    public Mapper getMapper() {
        return new MapperImpl();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Hotel Management CRS API",
                "Some custom description of API.",
                "1.0",
                "Terms of service",
                new Contact("Rahul Khapre", "www.baeldung.com", "khaprerahul0311@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
}
