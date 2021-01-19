package com.crs.microservices.proxy.model.payment;

import com.crs.microservices.proxy.model.payment.implementation.Card;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Card.class)
public interface ICard {

    public String getCardNumber();

    public void setCardNumber(String cardNumber);

    public String getExpMonth();

    public void setExpMonth(String expMonth);

    public String getExpYear();

    public void setExpYear(String expYear);
}
