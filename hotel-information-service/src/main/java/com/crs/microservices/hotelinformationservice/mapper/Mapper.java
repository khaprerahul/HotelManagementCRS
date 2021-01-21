package com.crs.microservices.hotelinformationservice.mapper;

import com.crs.microservices.hotelinformationservice.entity.HotelEntity;
import com.crs.microservices.hotelinformationservice.entity.ReservationEntity;
import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.Reservation;

public interface Mapper {
    HotelEntity mapHotelToHotelEntity(IHotel hotel);

    IHotel mapHotelEntityToHotel(HotelEntity hotelEntity);

    Reservation mapReservationEntityToReservation(ReservationEntity reservationEntity);

    ReservationEntity mapReservationToReservationEntity(Reservation reservation);

    //IGuest mapGuestToIGuest(com.hotel.proxy.model.IGuest guest);
}
