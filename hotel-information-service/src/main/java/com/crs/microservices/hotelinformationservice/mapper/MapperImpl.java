package com.crs.microservices.hotelinformationservice.mapper;

import com.crs.microservices.hotelinformationservice.entity.AddressEntity;
import com.crs.microservices.hotelinformationservice.entity.HotelEntity;
import com.crs.microservices.hotelinformationservice.entity.ReservationEntity;
import com.crs.microservices.hotelinformationservice.entity.RoomEntity;
import com.crs.microservices.hotelinformationservice.vo.*;

import java.util.stream.Collectors;
public class MapperImpl implements Mapper {
    @Override
    public HotelEntity mapIHotelToHotelVO(IHotel hotel) {
        HotelEntity hotelEntity =  new HotelEntity();
        hotelEntity.setAddress(mapIAddressToAddressDTO(hotel.getAddress()));
        hotelEntity.setRooms(hotel.getRooms().stream().map(this::mapIRoomToRoomDTO).collect(Collectors.toList()));
        hotelEntity.setReservations(hotel.getReservations().stream().map(this::mapReservationToReservationVO).collect(Collectors.toList()));
        hotelEntity.setHotelId(hotel.getHotelId());
        hotelEntity.setName(hotel.getName());
        hotelEntity.setPhoneNumber(hotel.getPhoneNumber());
        hotelEntity.setStarRatting(hotel.getRating());
        return hotelEntity;
    }

    public ReservationEntity mapReservationToReservationVO(Reservation reservation){
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setReservationId(reservation.getReservationId());
        reservationEntity.setState(reservation.getState().toString());
        reservationEntity.setFromDate(reservation.getFromDate());
        reservationEntity.setToDate(reservation.getToDate());
        reservationEntity.setGuestId(reservation.getGuestId());
        reservationEntity.setRoom( mapIRoomToRoomDTO(reservation.getRoom()));
        return reservationEntity;
    }

    private RoomEntity mapIRoomToRoomDTO(Room room)
    {
        RoomEntity roomEntity =  new RoomEntity();
        roomEntity.setRoomNo(room.getRoomNo());
        roomEntity.setRoomType(room.getRoomType());
        roomEntity.setRentPerNight(room.getRentPerNight());

        return roomEntity;
    }

    private AddressEntity mapIAddressToAddressDTO(Address address) {
        AddressEntity addressEntity =  new AddressEntity();
        addressEntity.setAddressId(address.getAddressId());
        addressEntity.setArea(address.getArea());
        addressEntity.setCity(address.getCity());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setPin(address.getPin());
        return addressEntity;
    }

    @Override
    public IHotel mapHotelVOToIHotel(HotelEntity hotelEntity) {
        IHotel iHotel =  new Hotel();

        iHotel.setHotelId(hotelEntity.getHotelId());
        iHotel.setName(hotelEntity.getName());
        iHotel.setPhoneNumber(hotelEntity.getPhoneNumber());
        iHotel.setRating(hotelEntity.getStarRatting());
        iHotel.setAddress(mapAddressDTOToIAddress(hotelEntity.getAddress()));
        iHotel.setRooms(hotelEntity.getRooms().stream().map(this::mapRoomDTOToIRoom).collect(Collectors.toList()));
        iHotel.setReservations(hotelEntity.getReservations().stream().map(this::mapReservationVOToReservation).collect(Collectors.toList()));
        //iHotel.setReservationsByDate(hotelDTO.getReservationsByDate());
        return iHotel;
    }

    private Address mapAddressDTOToIAddress(AddressEntity addressEntity){
        Address address = new AddressVO();
        address.setAddressId(addressEntity.getAddressId());
        address.setArea(addressEntity.getArea());
        address.setCity(addressEntity.getCity());
        address.setStreet(addressEntity.getStreet());
        address.setPin(addressEntity.getPin());
        return address;
    }

    private Room mapRoomDTOToIRoom(RoomEntity roomEntity){
        Room room = new RoomVO();
        if(roomEntity != null) {
            room.setRoomNo(roomEntity.getRoomNo());
            room.setRentPerNight(roomEntity.getRentPerNight());
            room.setRoomType(roomEntity.getRoomType());
        }
        return room;
    }

    public Reservation mapReservationVOToReservation(ReservationEntity reservationEntity){
        Reservation reservation = new ReservationVO();
        reservation.setReservationId(reservationEntity.getReservationId());
        reservation.setState(ReservationStatus.valueOf(reservationEntity.getState()));
        reservation.setGuestId(reservationEntity.getGuestId());
        //iReservation.setGuest(mapGuestDTOToIGuest(reservationDTO.getGuest()));
        reservation.setRoom(mapRoomDTOToIRoom(reservationEntity.getRoom()));
        reservation.setFromDate(reservationEntity.getFromDate());
        reservation.setToDate(reservationEntity.getToDate());
        return reservation;
    }
}
