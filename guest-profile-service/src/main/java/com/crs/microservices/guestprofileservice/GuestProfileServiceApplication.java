package com.crs.microservices.guestprofileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GuestProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestProfileServiceApplication.class, args);
	}

}
