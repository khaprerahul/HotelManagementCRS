package com.crs.microservices.hotelinformationservice.configuration;

import com.crs.microservices.hotelinformationservice.mapper.Mapper;
import com.crs.microservices.hotelinformationservice.mapper.MapperImpl;
import com.crs.microservices.hotelinformationservice.services.HotelService;
import com.crs.microservices.hotelinformationservice.services.HotelServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelConfiguration {

    @Bean
    public HotelService getHotelService(){
        return new HotelServiceImpl();
    }

    @Bean
    public Mapper getMapperForIHotelAndHotelDTO(){
        return new MapperImpl();
    }
}
