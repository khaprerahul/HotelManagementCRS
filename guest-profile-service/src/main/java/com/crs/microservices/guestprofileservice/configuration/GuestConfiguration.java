package com.crs.microservices.guestprofileservice.configuration;

import com.crs.microservices.guestprofileservice.mapper.Mapper;
import com.crs.microservices.guestprofileservice.service.GuestService;
import com.crs.microservices.guestprofileservice.mapper.MapperImpl;
import com.crs.microservices.guestprofileservice.service.GuestServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuestConfiguration {
    @Bean
    public GuestService getGuestService() {
        return new GuestServiceImpl();
    }

    @Bean
    public Mapper getMapper() {
        return new MapperImpl();
    }
}
