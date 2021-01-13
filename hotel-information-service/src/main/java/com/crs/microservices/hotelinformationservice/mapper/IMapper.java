package com.crs.microservices.hotelinformationservice.mapper;

import com.crs.microservices.hotelinformationservice.dto.HotelDTO;
import com.crs.microservices.hotelinformationservice.dto.ReservationDTO;
import com.crs.microservices.hotelinformationservice.model.IGuest;
import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.IReservation;

public interface IMapper {
	HotelDTO mapIHotelToHotelDTO(IHotel hotel);

	IHotel mapHotelDTOToIHotel(HotelDTO hotelDTO);

	IReservation mapReservationDTOToIReservation(ReservationDTO reservationDTO);

	ReservationDTO mapIReservationToReservationDTO(IReservation iReservation);

	IGuest mapGuestToIGuest(com.crs.microservices.hotelinformationservice.proxy.model.IGuest guest);
}
