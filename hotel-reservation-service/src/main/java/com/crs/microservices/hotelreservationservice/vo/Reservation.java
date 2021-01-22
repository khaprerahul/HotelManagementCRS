package com.crs.microservices.hotelreservationservice.vo;

import com.crs.microservices.hotelreservationservice.proxy.model.guest.ProxyGuest;
import com.crs.microservices.hotelreservationservice.proxy.model.hotel.ProxyHotel;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

@JsonDeserialize(as = ReservationVO.class)
public interface Reservation {
    public double getAmount();

    public void setAmount(double amount);

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

    public ReservationStatus getState();

    public void setState(ReservationStatus state);

    public String getRoomType();

    public void setRoomType(String roomType);

    public ProxyGuest getGuest();

    public void setGuest(ProxyGuest guest);

    public ProxyHotel getHotel();

    public void setHotel(ProxyHotel proxyHotel);
}
