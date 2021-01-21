package com.crs.microservices.hotelinformationservice.configuration;

import com.crs.microservices.hotelinformationservice.mapper.Mapper;
import com.crs.microservices.hotelinformationservice.mapper.MapperImpl;
import com.crs.microservices.hotelinformationservice.services.IHotelService;
import com.crs.microservices.hotelinformationservice.services.HotelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelConfiguration {

    @Bean
    public IHotelService getHotelService(){
        return new HotelService();
    }

    @Bean
    public Mapper getMapperForIHotelAndHotelDTO(){
        return new MapperImpl();
    }

}
