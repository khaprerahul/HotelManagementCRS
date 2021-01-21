package com.crs.microservices.hotelinformationservice.services;

import com.crs.microservices.hotelinformationservice.entity.HotelEntity;
import com.crs.microservices.hotelinformationservice.entity.ReservationEntity;
import com.crs.microservices.hotelinformationservice.mapper.Mapper;
import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.Reservation;
import com.crs.microservices.hotelinformationservice.model.Room;
import com.crs.microservices.hotelinformationservice.model.Hotel;
import com.crs.microservices.hotelinformationservice.repository.HotelRepositoryImpl;
import com.crs.microservices.hotelinformationservice.repository.ReservationRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepositoryImpl hotelRepository;

    @Autowired
    private ReservationRepositoryImpl reservationRepository;

    @Inject
    private Mapper mapper;

    Logger LOG = LoggerFactory.getLogger(HotelServiceImpl.class);

    public IHotel addNewHotel(IHotel hotel)
    {
        HotelEntity hotelEntity = mapper.mapHotelToHotelEntity(hotel);
        HotelEntity saved = hotelRepository.save(hotelEntity);
        return mapper.mapHotelEntityToHotel(saved);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Reservation confirmReservation(Long reservationId)
    {
        ReservationEntity reservationEntity = reservationRepository.findReservationById(reservationId);
        reservationEntity.setState("CONFIRMED");
        return mapper.mapReservationEntityToReservation(reservationEntity);
    }

    public List<Reservation> getReservationByGuestIdPerHotel(Long hotelId, Long guestId ){
        HotelEntity hotelEntity = hotelRepository.findById(hotelId);
        List<Reservation> reservations = hotelEntity.getReservations().stream().filter(reservationDTO -> reservationDTO.getGuestId() == guestId)
                    .map(mapper::mapReservationEntityToReservation).collect(Collectors.toList());
        return reservations;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Reservation cancelReservation(Long hotelId, Long reservationId){
        ReservationEntity reservation = reservationRepository.findReservationById(reservationId);
        reservation.setState("CANCELLED");

        return mapper.mapReservationEntityToReservation(reservation);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Reservation reservationRequest(Long hotelId, Reservation reservation){
        HotelEntity hotel = hotelRepository.findById(hotelId);
        reservation.setRoom(findAvailableRoom(mapper.mapHotelEntityToHotel(hotel), reservation.getFromDate(), reservation.getToDate()));
        hotel.getReservations().add(mapper.mapReservationToReservationEntity(reservation));
        return reservation;
    }

    private Room findAvailableRoom(IHotel hotel, Date fromDate, Date toDate){
        List<Room> reservedRooms = hotel.getReservations().stream().filter(iReservation -> iReservation.getFromDate().equals(fromDate))
                .map(iReservation -> iReservation.getRoom()).collect(Collectors.toList());

        Optional<Room> availableRoom = hotel.getRooms().stream().filter(room -> ! reservedRooms.contains(room)).findFirst();

        return availableRoom.isPresent() ? availableRoom.get():null;
    }

    public List<Reservation> getAllReservationsByHotelId(Long hotelId){
        HotelEntity hotel = hotelRepository.findById(hotelId);
        return hotel.getReservations().stream().map(mapper::mapReservationEntityToReservation).collect(Collectors.toList());
    }

    public List<IHotel> getHotels(List<Long> hotelIds){
        List<IHotel> hotels =  new ArrayList();
        for(Long hotelId : hotelIds){
            HotelEntity hotel = hotelRepository.findById(hotelId);
            hotels.add(mapper.mapHotelEntityToHotel(hotel));
        }
        return hotels;
    }

    public IHotel getHotelById(Long hotelId){
        IHotel hotel = new Hotel();
        HotelEntity hotelEntity =  hotelRepository.findById(hotelId);
        hotel = mapper.mapHotelEntityToHotel(hotelEntity);
        return hotel;
    }

}
