package com.crs.microservices.hotel.payment.service.controller;

import com.crs.microservices.hotel.payment.service.model.Card;
import com.crs.microservices.hotel.payment.service.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
public class PaymentControllerImpl implements com.crs.microservices.hotel.payment.service.controller.PaymentController {

    @Inject
    private PaymentService paymentService;

    @Override
    public String makePayment(Card card, double amount){
        return paymentService.makePayment(card, amount);
    }

    @Override
    public String revertPayment(Card card, double amount){
        return paymentService.revertPayment(card, amount);
    }

}
