package com.crs.microservices.hotelinformationservice.mapper;

import com.crs.microservices.hotelinformationservice.entity.HotelEntity;
import com.crs.microservices.hotelinformationservice.entity.ReservationEntity;
import com.crs.microservices.hotelinformationservice.vo.IHotel;
import com.crs.microservices.hotelinformationservice.vo.Reservation;

public interface Mapper {
    HotelEntity mapIHotelToHotelVO(IHotel hotel);

    IHotel mapHotelVOToIHotel(HotelEntity hotelEntity);

    Reservation mapReservationVOToReservation(ReservationEntity reservationEntity);

    ReservationEntity mapReservationToReservationVO(Reservation reservation);

}
