package com.crs.microservices.service;

import com.crs.microservices.proxy.model.guest.Guest;
import com.crs.microservices.proxy.model.hotel.Hotel;
import com.crs.microservices.model.Card;
import com.crs.microservices.model.Reservation;


public interface ReservationService {
    public Guest getGuestById(Long guestId);

    public Hotel getHotelById(Long hotelId);

    public String requestForReservation(Reservation reservation);

    public Reservation confirmReservation(Reservation reservation);

    public Reservation getReservation(Long id, boolean isDetailsRequired);

    public Reservation cancelReservation(Long id, double amount) throws Exception;
}
