package com.crs.microservices.guestprofileservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.crs.microservices.guestprofileservice.mapper.IMapper;
import com.crs.microservices.guestprofileservice.mapper.Mapper;
import com.crs.microservices.guestprofileservice.services.GuestService;
import com.crs.microservices.guestprofileservice.services.GuestServiceImpl;

@Configuration
public class GuestConfiguration {
	@Bean
	public GuestService getGuestService() {
		return new GuestServiceImpl();
	}

	@Bean
	public IMapper getMapper() {
		return new Mapper();
	}
}
