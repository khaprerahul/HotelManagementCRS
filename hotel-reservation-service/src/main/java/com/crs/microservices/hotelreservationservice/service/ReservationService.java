package com.crs.microservices.hotelreservationservice.service;

import com.crs.microservices.hotelreservationservice.exception.ReservationEntityNotFoundException;
import com.crs.microservices.hotelreservationservice.proxy.model.guest.ProxyGuest;
import com.crs.microservices.hotelreservationservice.proxy.model.hotel.ProxyHotel;
import com.crs.microservices.hotelreservationservice.vo.Card;
import com.crs.microservices.hotelreservationservice.vo.Guest;
import com.crs.microservices.hotelreservationservice.vo.Reservation;


public interface ReservationService {
    public ProxyGuest getGuestById(Long guestId) throws Exception;

    public ProxyHotel getHotelById(Long hotelId);

    public Reservation requestForReservation(Reservation reservation) throws Exception;

    public Reservation updateReservation(Reservation reservation) throws ReservationEntityNotFoundException;

    public Reservation getReservation(Long id) throws Exception;

    Guest getReservationsByGuestId(Long guestId);

    //public IReservation cancelReservation(Long id, double amount) throws Exception;
}
