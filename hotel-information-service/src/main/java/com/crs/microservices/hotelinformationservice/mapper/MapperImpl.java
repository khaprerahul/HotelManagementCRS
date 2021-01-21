package com.crs.microservices.hotelinformationservice.mapper;

import com.crs.microservices.hotelinformationservice.entity.AddressEntity;
import com.crs.microservices.hotelinformationservice.entity.HotelEntity;
import com.crs.microservices.hotelinformationservice.entity.ReservationEntity;
import com.crs.microservices.hotelinformationservice.entity.RoomEntity;
import com.crs.microservices.hotelinformationservice.model.Address;
import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.Reservation;
import com.crs.microservices.hotelinformationservice.model.Room;
import com.crs.microservices.hotelinformationservice.model.AddressImpl;
import com.crs.microservices.hotelinformationservice.model.Hotel;
import com.crs.microservices.hotelinformationservice.model.ReservationImpl;
import com.crs.microservices.hotelinformationservice.model.RoomImpl;


import java.util.stream.Collectors;

public class MapperImpl implements Mapper {
    @Override
    public HotelEntity mapHotelToHotelEntity(IHotel hotel) {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setAddress(mapAddressToAddressEntity(hotel.getAddress()));
        hotelEntity.setRooms(hotel.getRooms().stream().map(this::mapRoomToRoomEntity).collect(Collectors.toList()));
        hotelEntity.setReservations(hotel.getReservations().stream().map(this::mapReservationToReservationEntity).collect(Collectors.toList()));
        hotelEntity.setHotelId(hotel.getHotelId());
        hotelEntity.setName(hotel.getName());
        hotelEntity.setPhoneNumber(hotel.getPhoneNumber());
        hotelEntity.setStarRatting(hotel.getStarRatting());
        return hotelEntity;
    }

    public ReservationEntity mapReservationToReservationEntity(Reservation reservation) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setReservationId(reservation.getReservationId());
        reservationEntity.setState(reservation.getState());
        reservationEntity.setFromDate(reservation.getFromDate());
        reservationEntity.setToDate(reservation.getToDate());
        reservationEntity.setGuestId(reservation.getGuestId());
        reservationEntity.setRoom(mapRoomToRoomEntity(reservation.getRoom()));
        return reservationEntity;
    }

    private RoomEntity mapRoomToRoomEntity(Room room) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNo(room.getRoomNo());
        roomEntity.setRoomType(room.getRoomType());
        roomEntity.setRentPerNight(room.getRentPerNight());

        return roomEntity;
    }

    private AddressEntity mapAddressToAddressEntity(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(address.getAddressId());
        addressEntity.setArea(address.getArea());
        addressEntity.setCity(address.getCity());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setPin(address.getPin());
        return addressEntity;
    }

    @Override
    public IHotel mapHotelEntityToHotel(HotelEntity hotelEntity) {
        IHotel iHotel = new Hotel();

        iHotel.setHotelId(hotelEntity.getHotelId());
        iHotel.setName(hotelEntity.getName());
        iHotel.setPhoneNumber(hotelEntity.getPhoneNumber());
        iHotel.setStarRatting(hotelEntity.getStarRatting());
        iHotel.setAddress(mapAddressDTOToIAddress(hotelEntity.getAddress()));
        iHotel.setRooms(hotelEntity.getRooms().stream().map(this::mapRoomDTOToIRoom).collect(Collectors.toList()));
        iHotel.setReservations(hotelEntity.getReservations().stream().map(this::mapReservationEntityToReservation).collect(Collectors.toList()));
        //iHotel.setReservationsByDate(hotelDTO.getReservationsByDate());
        return iHotel;
    }

    private Address mapAddressDTOToIAddress(AddressEntity addressEntity) {
        Address address = new AddressImpl();
        address.setAddressId(addressEntity.getAddressId());
        address.setArea(addressEntity.getArea());
        address.setCity(addressEntity.getCity());
        address.setStreet(addressEntity.getStreet());
        address.setPin(addressEntity.getPin());
        return address;
    }

    private Room mapRoomDTOToIRoom(RoomEntity roomEntity) {
        Room room = new RoomImpl();
        if (roomEntity != null) {
            room.setRoomNo(roomEntity.getRoomNo());
            room.setRentPerNight(roomEntity.getRentPerNight());
            room.setRoomType(roomEntity.getRoomType());
        }
        return room;
    }

   /* private Long mapGuestDTOToIGuest(GuestDTO guestDTO){
        Long iGuest = new Guest();
        if(guestDTO != null) {
            iGuest.setGuestId(guestDTO.getGuestId());
            iGuest.setContactNumber(guestDTO.getContactNumber());
            iGuest.setName(guestDTO.getName());
        }
        return iGuest;
    }*/

    public Reservation mapReservationEntityToReservation(ReservationEntity reservationEntity) {
        Reservation reservation = new ReservationImpl();
        reservation.setReservationId(reservationEntity.getReservationId());
        reservation.setState(reservationEntity.getState());
        reservation.setGuestId(reservationEntity.getGuestId());
        //iReservation.setGuest(mapGuestDTOToIGuest(reservationDTO.getGuest()));
        reservation.setRoom(mapRoomDTOToIRoom(reservationEntity.getRoom()));
        reservation.setFromDate(reservationEntity.getFromDate());
        reservation.setToDate(reservationEntity.getToDate());
        return reservation;
    }
}
