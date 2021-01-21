package com.crs.microservices.hotelinformationservice.services;

import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.Reservation;

import java.util.Date;
import java.util.List;

public interface HotelService {
    IHotel addNewHotel(IHotel hotel);
    Reservation cancelReservation(Long hotelId, Long reservationId);
    Reservation reservationRequest(Long hotelId, Reservation reservation);
    List<Reservation> getAllReservationsByHotelId(Long hotelId);
    List<Reservation> getReservationByGuestIdPerHotel(Long hotelId, Long guestId );
    public Reservation confirmReservation(Long reservationId);

    public List<IHotel> getHotels(List<Long> hotelIds);
    public IHotel getHotelById(Long hotelId);
}
