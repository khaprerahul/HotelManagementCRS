package com.crs.microservices.hotelinformationservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.crs.microservices.hotelinformationservice.mapper.IMapper;
import com.crs.microservices.hotelinformationservice.mapper.Mapper;
import com.crs.microservices.hotelinformationservice.services.HotelService;
import com.crs.microservices.hotelinformationservice.services.HotelServiceImpl;

@Configuration
public class HotelConfiguration {
	@Bean
	public HotelService getHotelService() {
		return new HotelServiceImpl();
	}

	@Bean
	public IMapper getMapperForIHotelAndHotelDTO() {
		return new Mapper();
	}
}
