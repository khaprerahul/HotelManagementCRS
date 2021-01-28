package com.crs.microservices.hotel.payment.service.configuration;

import com.crs.microservices.hotel.payment.service.service.PaymentServiceImpl;
import com.crs.microservices.hotel.payment.service.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    @Bean
    public PaymentService getPaymentService(){
        return new PaymentServiceImpl();
    }

}
