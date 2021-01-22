package com.crs.microservices.hotelreservationservice.configuration;

import com.crs.microservices.hotelreservationservice.mapper.Mapper;
import com.crs.microservices.hotelreservationservice.mapper.MapperImpl;
import com.crs.microservices.hotelreservationservice.service.ReservationServiceImpl;
import com.crs.microservices.hotelreservationservice.service.ReservationService;
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
