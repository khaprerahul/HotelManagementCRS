package com.crs.microservices.hotel.payment.service.service;

import com.crs.microservices.hotel.payment.service.model.Card;

public interface PaymentService {

    public String makePayment(Card card, double amount);

    String revertPayment(Card card, double amount);
}
