package com.crs.microservices.hotel.payment.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class HotelPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelPaymentServiceApplication.class, args);
	}

}
