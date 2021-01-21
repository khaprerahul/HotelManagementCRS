package com.crs.microservices.hotelinformationservice.mapper;

import com.crs.microservices.hotelinformationservice.entity.HotelEntity;
import com.crs.microservices.hotelinformationservice.entity.ReservationEntity;
import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.Reservation;

public interface IMapper {
    HotelEntity mapIHotelToHotelDTO(IHotel hotel);

    IHotel mapHotelDTOToIHotel(HotelEntity hotelEntity);

    Reservation mapReservationDTOToIReservation(ReservationEntity reservationEntity);

    ReservationEntity mapIReservationToReservationDTO(Reservation reservation);

    //IGuest mapGuestToIGuest(com.hotel.proxy.model.IGuest guest);
}
