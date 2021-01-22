package com.crs.microservices.hotelreservationservice.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(as = GuestVO.class)
public interface Guest {
    public Long getGuestId();

    public void setGuestId(Long guestId);

    public String getName();

    public void setName(String name) ;

    public String getEmail();

    public void setEmail(String email);

    public String getContactNumber();

    public void setContactNumber(String contactNumber) ;

    public int getRatting();

    public void setRatting(int ratting);

    public List<Reservation> getReservations();

    public void setReservations(List<Reservation> reservations);

}
