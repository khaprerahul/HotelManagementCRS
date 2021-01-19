package com.crs.microservices.model;

import com.crs.microservices.proxy.model.guest.Guest;
import com.crs.microservices.proxy.model.hotel.Hotel;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

@JsonDeserialize(as = ReservationImpl.class)
public interface Reservation {

    public Card getCard();

    public void setCard(Card card);
    public Date getFromDate();

    public void setFromDate(Date fromDate);

    public Date getToDate();

    public void setToDate(Date toDate);

    public Long getGuestId();

    public void setGuestId(Long guestId);

    public Long getHotelId();

    public void setHotelId(Long hotelId);

    public Long getReservationId();

    public void setReservationId(Long reservationId);

    public String getState();

    public void setState(String state);

    public String getRoomType();

    public void setRoomType(String roomType);

    public Guest getGuest();

    public void setGuest(Guest guest);

    public Hotel getHotel();

    public void setHotel(Hotel hotel);
}
