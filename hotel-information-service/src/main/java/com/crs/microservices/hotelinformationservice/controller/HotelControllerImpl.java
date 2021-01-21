package com.crs.microservices.hotelinformationservice.controller;

import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.Reservation;
import com.crs.microservices.hotelinformationservice.model.Hotel;
import com.crs.microservices.hotelinformationservice.services.IHotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;

@RestController
public class HotelControllerImpl implements HotelController {

    @Inject
    private IHotelService service;

    @Override
    public ResponseEntity<IHotel> addNewHotel(Hotel hotel)
    {
        IHotel response = service.addNewHotel(hotel);
        URI uri = URI.create(String.format("/hotel/%s", response.getHotelId()));
        return ResponseEntity.created(uri).body(response);
    }

    @Override
    public ResponseEntity<Reservation> reservationRequest(Reservation reservation, Long hotelId){
        return ResponseEntity.ok(service.reservationRequest(hotelId, reservation)) ;
    }

    @Override
    public ResponseEntity<Reservation> updateReservation(Long hotelId, Reservation reservation) {
        Reservation reservationResult = service.updateReservation(hotelId, reservation);
        return ResponseEntity.accepted().body(reservationResult);
    }

    @Override
    public ResponseEntity<List<Reservation>> getAllReservationsByHotelId(Long hotelId){
        return ResponseEntity.ok(service.getAllReservationsByHotelId(hotelId));
    }

    @Override
    public List<Reservation> getReservationByGuest(Long hotelId, Long guestId)
    {
        List<Reservation> reservations = service.getReservationByGuestIdPerHotel(hotelId, guestId);
        return reservations;
    }

    @Override
    public List<IHotel> getHotels(List<Long> hotelIds){
        return service.getHotels(hotelIds);
    }

    @Override
    public IHotel getHotelById(Long hotelId){
        return service.getHotelById(hotelId);
    }

    @Override
    public List<IHotel> searchHotels(String cityName) {
        List<IHotel> hotels = service.searchHotelsByCity(cityName);
        return hotels;
    }

}