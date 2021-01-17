package com.crs.microservices.hotelinformationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HotelInformationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelInformationServiceApplication.class, args);
	}

}
