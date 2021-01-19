package com.crs.microservices.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@JsonDeserialize(as = CardImpl.class)
public interface Card {

    public String getCardNumber();

    public void setCardNumber(String cardNumber);

    public String getExpMonth();

    public void setExpMonth(String expMonth);

    public String getExpYear();

    public void setExpYear(String expYear);
}
