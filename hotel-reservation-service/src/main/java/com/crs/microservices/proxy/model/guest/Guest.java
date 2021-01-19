package com.crs.microservices.proxy.model.guest;

import com.crs.microservices.proxy.model.payment.ICard;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(as = GuestImpl.class)
public interface Guest {

    public Long getGuestId();

    public void setGuestId(Long guestId);

    public String getName();

    public void setName(String name) ;

    public String getEmail() ;

    public void setEmail(String email);

    public String getContactNumber();

    public void setContactNumber(String contactNumber);

    public int getRatting();

    public void setRatting(int ratting);

    public List<ICard> getCard();

    public void setCard(List<ICard> card);
}
