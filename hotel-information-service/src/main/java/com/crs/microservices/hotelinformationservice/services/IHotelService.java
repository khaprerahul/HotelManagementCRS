package com.crs.microservices.hotelinformationservice.services;

import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.Reservation;

import java.util.List;

public interface IHotelService {
    IHotel addNewHotel(IHotel hotel);
    Reservation reservationRequest(Long hotelId, Reservation reservation);
    List<Reservation> getAllReservationsByHotelId(Long hotelId);
    List<Reservation> getReservationByGuestIdPerHotel(Long hotelId, Long guestId );


    public List<IHotel> getHotels(List<Long> hotelIds);
    public IHotel getHotelById(Long hotelId);

    //public List<IHotel> searchHotels(String city, Date fromDate, Date toDate, String roomType );

    Reservation updateReservation(Long hotelId, Reservation reservation);

    List<IHotel> searchHotelsByCity(String cityName);
}


