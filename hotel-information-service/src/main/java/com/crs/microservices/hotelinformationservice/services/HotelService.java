package com.crs.microservices.hotelinformationservice.services;

import com.crs.microservices.hotelinformationservice.entity.HotelEntity;
import com.crs.microservices.hotelinformationservice.entity.ReservationEntity;
import com.crs.microservices.hotelinformationservice.vo.Hotel;
import com.crs.microservices.hotelinformationservice.vo.IHotel;
import com.crs.microservices.hotelinformationservice.vo.Reservation;
import com.crs.microservices.hotelinformationservice.repository.HotelRepositoryImpl;
import com.crs.microservices.hotelinformationservice.repository.ReservationRepositoryImpl;
import com.crs.microservices.hotelinformationservice.mapper.Mapper;
import com.crs.microservices.hotelinformationservice.vo.Room;
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

public class HotelService implements IHotelService {

    @Autowired
    private HotelRepositoryImpl hotelRepository;

    @Autowired
    private ReservationRepositoryImpl reservationRepository;

    @Inject
    private Mapper imapper;

    Logger LOG = LoggerFactory.getLogger(HotelService.class);

    public IHotel addNewHotel(IHotel hotel) {
        HotelEntity hotelEntity = imapper.mapIHotelToHotelVO(hotel);
        HotelEntity saved = hotelRepository.save(hotelEntity);
        return imapper.mapHotelVOToIHotel(saved);
    }

    public List<Reservation> getReservationByGuestIdPerHotel(Long hotelId, Long guestId) {
        HotelEntity hotelEntity = hotelRepository.findById(hotelId);
        return hotelEntity.getReservations().stream().filter(reservationDTO -> reservationDTO.getGuestId() == guestId)
                .map(imapper::mapReservationVOToReservation).collect(Collectors.toList());
    }

    public Reservation reservationRequest(Long hotelId, Reservation reservation) {
        HotelEntity hotel = hotelRepository.findById(hotelId);
        reservation.setRoom(findAvailableRoom(imapper.mapHotelVOToIHotel(hotel), reservation.getFromDate(), reservation.getToDate()));
        hotel.getReservations().add(imapper.mapReservationToReservationVO(reservation));
        return reservation;
    }

    private Room findAvailableRoom(IHotel hotel, Date fromDate, Date toDate) {
        List<Room> reservedRooms = hotel.getReservations().stream().filter(iReservation -> iReservation.getFromDate().equals(fromDate))
                .map(iReservation -> iReservation.getRoom()).collect(Collectors.toList());

        Optional<Room> availableRoom = hotel.getRooms().stream().filter(room -> {
            return !reservedRooms.contains(room);
        }).findFirst();

        return availableRoom.isPresent() ? availableRoom.get() : null;
    }

    public List<Reservation> getAllReservationsByHotelId(Long hotelId) {
        HotelEntity hotel = hotelRepository.findById(hotelId);
        return hotel.getReservations().stream().map(imapper::mapReservationVOToReservation).collect(Collectors.toList());
    }

    public List<IHotel> getHotels(List<Long> hotelIds) {
        List<IHotel> hotels = new ArrayList();
        for (Long hotelId : hotelIds) {
            HotelEntity hotel = hotelRepository.findById(hotelId);
            hotels.add(imapper.mapHotelVOToIHotel(hotel));
        }
        return hotels;
    }

    public IHotel getHotelById(Long hotelId) {
        IHotel hotel = new Hotel();
        HotelEntity hotelEntity = hotelRepository.findById(hotelId);
        hotel = imapper.mapHotelVOToIHotel(hotelEntity);
        return hotel;
    }

    @Override
    public Reservation updateReservation(Long hotelId, Reservation reservation) {
        ReservationEntity reservationEntity = reservationRepository.findReservationById(reservation.getReservationId());
        reservationEntity.setState(reservation.getState().toString());
        return imapper.mapReservationVOToReservation(reservationEntity);
    }

    @Override
    public List<IHotel> searchHotelsByCity(String cityName) {
        List<HotelEntity> allHotelsEntity = hotelRepository.getAllHotels();
        List<IHotel> resultingHotels = allHotelsEntity.stream().filter(h -> h.getAddress().getCity().equalsIgnoreCase(cityName))
                .map(imapper::mapHotelVOToIHotel)
                .collect(Collectors.toList());
        return resultingHotels;
    }


}
