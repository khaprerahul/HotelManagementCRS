package com.crs.microservices.hotel.payment.service.controller;

import com.crs.microservices.hotel.payment.service.model.Card;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/payments")
public interface PaymentController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String makePayment(@RequestBody Card card, @RequestParam("amount") double amount);

    @PostMapping(value = "/revert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String revertPayment(@RequestBody Card card, @RequestParam("amount") double amount);

}
