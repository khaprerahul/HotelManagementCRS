package com.crs.microservices.hotel.payment.service.service;

import com.crs.microservices.hotel.payment.service.model.Card;
import com.crs.microservices.hotel.payment.service.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    public String makePayment(Card card, double amount){
        return "SUCCESS";
    }

    @Override
    public String revertPayment(Card card, double amount) {
        return "SUCCESS";
    }

}
