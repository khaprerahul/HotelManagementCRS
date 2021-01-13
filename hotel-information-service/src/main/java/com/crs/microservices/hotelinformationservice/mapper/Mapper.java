package com.crs.microservices.hotelinformationservice.mapper;

import java.util.stream.Collectors;

import com.crs.microservices.hotelinformationservice.dto.AddressDTO;
import com.crs.microservices.hotelinformationservice.dto.HotelDTO;
import com.crs.microservices.hotelinformationservice.dto.ReservationDTO;
import com.crs.microservices.hotelinformationservice.dto.RoomDTO;
import com.crs.microservices.hotelinformationservice.model.Address;
import com.crs.microservices.hotelinformationservice.model.Guest;
import com.crs.microservices.hotelinformationservice.model.Hotel;
import com.crs.microservices.hotelinformationservice.model.IAddress;
import com.crs.microservices.hotelinformationservice.model.IGuest;
import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.IReservation;
import com.crs.microservices.hotelinformationservice.model.IRoom;
import com.crs.microservices.hotelinformationservice.model.Reservation;
import com.crs.microservices.hotelinformationservice.model.Room;

public class Mapper implements IMapper {

	@Override
	public HotelDTO mapIHotelToHotelDTO(IHotel hotel) {
		HotelDTO hotelDTO = new HotelDTO();
		hotelDTO.setAddress(mapIAddressToAddressDTO(hotel.getAddress()));
		hotelDTO.setRooms(hotel.getRooms().stream().map(this::mapIRoomToRoomDTO).collect(Collectors.toList()));
		hotelDTO.setReservations(hotel.getReservations().stream().map(this::mapIReservationToReservationDTO)
				.collect(Collectors.toList()));
		hotelDTO.setHotelId(hotel.getHotelId());
		hotelDTO.setName(hotel.getName());
		hotelDTO.setPhoneNumber(hotel.getPhoneNumber());
		hotelDTO.setStarRatting(hotel.getStarRatting());
		// hotelDTO.setReservationsByDate(hotel.getReservationsByDate());
		return hotelDTO;
	}

	public ReservationDTO mapIReservationToReservationDTO(IReservation iReservation) {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setReservationId(iReservation.getReservationId());
		reservationDTO.setState(iReservation.getState());
		reservationDTO.setFromDate(iReservation.getFromDate());
		reservationDTO.setToDate(iReservation.getToDate());
		reservationDTO.setGuestId(iReservation.getGuestId());
		reservationDTO.setRoom(mapIRoomToRoomDTO(iReservation.getRoom()));
		return reservationDTO;
	}

	@Override
	public com.crs.microservices.hotelinformationservice.model.IGuest mapGuestToIGuest(
			com.crs.microservices.hotelinformationservice.proxy.model.IGuest guest) {
		com.crs.microservices.hotelinformationservice.model.IGuest iGuest = new Guest();
		iGuest.setName(guest.getName());
		iGuest.setGuestId(guest.getGuestId());
		iGuest.setContactNumber(guest.getContactNumber());
		iGuest.setEmail(guest.getEmail());
		return iGuest;
	}

	/*
	 * private GuestDTO mapIGuestToGuestDTO(IGuest iGuest){ GuestDTO guestDTO = new
	 * GuestDTO(); guestDTO.setGuestId(iGuest.getGuestId());
	 * guestDTO.setName(iGuest.getName());
	 * guestDTO.setContactNumber(iGuest.getContactNumber()); return guestDTO; }
	 */

	private RoomDTO mapIRoomToRoomDTO(IRoom iRoom) {
		RoomDTO roomDTO = new RoomDTO();
		roomDTO.setRoomNo(iRoom.getRoomNo());
		roomDTO.setRoomType(iRoom.getRoomType());
		roomDTO.setRentPerNight(iRoom.getRentPerNight());

		return roomDTO;
	}

	private AddressDTO mapIAddressToAddressDTO(IAddress address) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressId(address.getAddressId());
		addressDTO.setArea(address.getArea());
		addressDTO.setCity(address.getCity());
		addressDTO.setStreet(address.getStreet());

		return addressDTO;
	}

	@Override
	public IHotel mapHotelDTOToIHotel(HotelDTO hotelDTO) {
		IHotel iHotel = new Hotel();

		iHotel.setHotelId(hotelDTO.getHotelId());
		iHotel.setName(hotelDTO.getName());
		iHotel.setPhoneNumber(hotelDTO.getPhoneNumber());
		iHotel.setStarRatting(hotelDTO.getStarRatting());
		iHotel.setAddress(mapAddressDTOToIAddress(hotelDTO.getAddress()));
		iHotel.setRooms(hotelDTO.getRooms().stream().map(this::mapRoomDTOToIRoom).collect(Collectors.toList()));
		iHotel.setReservations(hotelDTO.getReservations().stream().map(this::mapReservationDTOToIReservation)
				.collect(Collectors.toList()));
		// iHotel.setReservationsByDate(hotelDTO.getReservationsByDate());
		return iHotel;
	}

	private IAddress mapAddressDTOToIAddress(AddressDTO addressDTO) {
		IAddress iAddress = new Address();
		iAddress.setAddressId(addressDTO.getAddressId());
		iAddress.setArea(addressDTO.getArea());
		iAddress.setCity(addressDTO.getCity());
		iAddress.setStreet(addressDTO.getStreet());
		return iAddress;
	}

	private IRoom mapRoomDTOToIRoom(RoomDTO roomDTO) {
		IRoom iRoom = new Room();
		if (roomDTO != null) {
			iRoom.setRoomNo(roomDTO.getRoomNo());
			iRoom.setRentPerNight(roomDTO.getRentPerNight());
			iRoom.setRoomType(roomDTO.getRoomType());
		}
		return iRoom;
	}

	/*
	 * private Long mapGuestDTOToIGuest(GuestDTO guestDTO){ Long iGuest = new
	 * Guest(); if(guestDTO != null) { iGuest.setGuestId(guestDTO.getGuestId());
	 * iGuest.setContactNumber(guestDTO.getContactNumber());
	 * iGuest.setName(guestDTO.getName()); } return iGuest; }
	 */

	public IReservation mapReservationDTOToIReservation(ReservationDTO reservationDTO) {
		IReservation iReservation = new Reservation();
		iReservation.setReservationId(reservationDTO.getReservationId());
		iReservation.setState(reservationDTO.getState());
		iReservation.setGuestId(reservationDTO.getGuestId());
		// iReservation.setGuest(mapGuestDTOToIGuest(reservationDTO.getGuest()));
		iReservation.setRoom(mapRoomDTOToIRoom(reservationDTO.getRoom()));
		iReservation.setFromDate(reservationDTO.getFromDate());
		iReservation.setToDate(reservationDTO.getToDate());
		return iReservation;
	}
}
