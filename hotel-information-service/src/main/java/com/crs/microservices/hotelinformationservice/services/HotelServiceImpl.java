/**
 * 
 */
package com.crs.microservices.hotelinformationservice.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.crs.microservices.hotelinformationservice.dto.HotelDTO;
import com.crs.microservices.hotelinformationservice.dto.ReservationDTO;
import com.crs.microservices.hotelinformationservice.mapper.IMapper;
import com.crs.microservices.hotelinformationservice.model.Hotel;
import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.IReservation;
import com.crs.microservices.hotelinformationservice.model.IRoom;
import com.crs.microservices.hotelinformationservice.model.Reservation;
import com.crs.microservices.hotelinformationservice.proxy.GuestInformationProxy;
import com.crs.microservices.hotelinformationservice.repository.HotelRepository;
import com.crs.microservices.hotelinformationservice.repository.ReservationRepository;

/**
 * @author Rahul_Khapre
 *
 */
public class HotelServiceImpl implements HotelService {

	@Autowired
	private GuestInformationProxy guestProxy;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Inject
	private IMapper imapper;

	Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);

	public IHotel addNewHotel(IHotel hotel) {
		HotelDTO hotelDTO = imapper.mapIHotelToHotelDTO(hotel);
		HotelDTO saved = hotelRepository.save(hotelDTO);
		return imapper.mapHotelDTOToIHotel(saved);
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public String confirmReservation(Long reservationId) {
		Optional<ReservationDTO> reservationDTO = reservationRepository.findById(reservationId);
		if (reservationDTO.isPresent()) {
			reservationDTO.get().setState("DONE");
			return "Reservation Confirmed.";
		}
		return "Reservation information not found.";
	}

	public List<IReservation> getReservationByGuestIdPerHotel(Long hotelId, Long guestId) {
		Optional<HotelDTO> hotelDTO = hotelRepository.findById(hotelId);
		if (hotelDTO.isPresent()) {
			List<IReservation> reservations = hotelDTO.get().getReservations().stream()
					.filter(reservationDTO -> reservationDTO.getGuestId() == guestId)
					.map(imapper::mapReservationDTOToIReservation).collect(Collectors.toList());
			return reservations;
		}
		return null;
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public String cancelReservation(Long hotelId, Long reservationId) {
		Optional<HotelDTO> hotel = hotelRepository.findById(hotelId);
		if (hotel.isPresent()) {
			Optional<ReservationDTO> reservationToCancel = hotel.get().getReservations().stream()
					.filter(reservation -> reservation.getReservationId() == reservationId).findFirst();
			if (reservationToCancel.isPresent()) {
				reservationToCancel.get().setState("CANCELLED");
				// Reservation reservation = reservationRepository.
				return "Reservation cancelled ";
			}
		}
		return "No reservation with id " + reservationId + " found.";
	}

	public void updateReservation(Reservation reservation) {

	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public String reservationRequest(Long hotelId, IReservation reservation) {
		Optional<HotelDTO> hotel = hotelRepository.findById(hotelId);
		if (hotel.isPresent()) {
			reservation.setRoom(findAvailableRoom(imapper.mapHotelDTOToIHotel(hotel.get()), reservation.getFromDate(),
					reservation.getToDate()));
			hotel.get().getReservations().add(imapper.mapIReservationToReservationDTO(reservation));
			return "Reservation Request accepted.";
		}
		return "Reservation request rejected as Hotel information is wrong.";
	}

	/*
	 * private boolean isRoomAvailableForDates(HotelDTO hotel, Date fromDate, Date
	 * toDate){ Date dateToCheck = fromDate; while (dateToCheck.after(toDate) ||
	 * dateToCheck.equals(toDate)){ List<Long> reservedRooms =
	 * hotel.getReservationsByDate().get(dateToCheck); if(reservedRooms.size() <
	 * hotel.getRooms().size()) dateToCheck.setDate(dateToCheck.getDate() + 1); else
	 * return false; } return true; }
	 */

	private IRoom findAvailableRoom(IHotel hotel, Date fromDate, Date toDate) {
		List<IRoom> reservedRooms = hotel.getReservations().stream()
				.filter(iReservation -> iReservation.getFromDate().equals(fromDate))
				.map(iReservation -> iReservation.getRoom()).collect(Collectors.toList());

		Optional<IRoom> availableRoom = hotel.getRooms().stream().filter(room -> !reservedRooms.contains(room))
				.findFirst();

		return availableRoom.get();
	}

	public List<IReservation> getAllReservationsByHotelId(Long hotelId) {
		Optional<HotelDTO> hotel = hotelRepository.findById(hotelId);
		if (hotel.isPresent()) {
			return hotel.get().getReservations().stream().map(imapper::mapReservationDTOToIReservation)
					.collect(Collectors.toList());
		}
		return null;
	}

	public List<IHotel> getHotels(List<Long> hotelIds) {
		List<IHotel> hotels = new ArrayList<>();
		for (Long hotelId : hotelIds) {
			Optional<HotelDTO> hotel = hotelRepository.findById(hotelId);
			if (hotel.isPresent()) {
				hotels.add(imapper.mapHotelDTOToIHotel(hotel.get()));
			}
		}
		return hotels;
	}

	public IHotel getHotelById(Long hotelId) {
		IHotel hotel = new Hotel();
		Optional<HotelDTO> hotelDTO = hotelRepository.findById(hotelId);
		if (hotelDTO.isPresent()) {
			hotel = imapper.mapHotelDTOToIHotel(hotelDTO.get());
		}
		return hotel;
	}

	public List<IHotel> searchHotels(String city, Date fromDate, Date toDate, String roomType) {
		List<HotelDTO> hotels = hotelRepository.findAll();
		List<IHotel> outputHotels = hotels.stream().filter(h -> h.getAddress().getCity().equals(city))
				.map(imapper::mapHotelDTOToIHotel).collect(Collectors.toList());
		return outputHotels;
	}

}
