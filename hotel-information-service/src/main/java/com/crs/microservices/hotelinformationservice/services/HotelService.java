package com.crs.microservices.hotelinformationservice.services;

import java.util.Date;
import java.util.List;

import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.IReservation;

public interface HotelService {
	IHotel addNewHotel(IHotel hotel);

	String cancelReservation(Long hotelId, Long reservationId);

	String reservationRequest(Long hotelId, IReservation reservation);

	List<IReservation> getAllReservationsByHotelId(Long hotelId);

	List<IReservation> getReservationByGuestIdPerHotel(Long hotelId, Long guestId);

	public String confirmReservation(Long reservationId);
	// public List<IHotel> getHotelsByCityAndDateRange(String cityName, Date
	// fromDate, Date toDate);

	public List<IHotel> getHotels(List<Long> hotelIds);

	public IHotel getHotelById(Long hotelId);

	public List<IHotel> searchHotels(String city, Date fromDate, Date toDate, String roomType);
}
