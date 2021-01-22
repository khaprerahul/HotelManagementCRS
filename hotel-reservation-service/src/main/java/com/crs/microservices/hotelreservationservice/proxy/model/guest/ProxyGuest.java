package com.crs.microservices.hotelreservationservice.proxy.model.guest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.crs.microservices.hotelreservationservice.proxy.model.card.ICard;

import java.util.List;

@JsonDeserialize(as = ProxyGuestImpl.class)
public interface ProxyGuest {

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

    public void setReservations(List<Long> reservations);

    public List<Long> getReservations();
}
