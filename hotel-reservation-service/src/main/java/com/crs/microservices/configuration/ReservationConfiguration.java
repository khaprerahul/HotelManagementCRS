package com.crs.microservices.configuration;

import com.crs.microservices.mapper.MapperImpl;
import com.crs.microservices.service.ReservationServiceImpl;
import com.crs.microservices.mapper.Mapper;
import com.crs.microservices.service.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationConfiguration {

    @Bean
    public ReservationService getReservationService(){
        return new ReservationServiceImpl();
    }

    @Bean
    public Mapper getMapper(){
        return new MapperImpl();
    }
}
