package com.crs.microservices.hotelinformationservice.controller;

import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.Reservation;
import com.crs.microservices.hotelinformationservice.model.Hotel;
import com.crs.microservices.hotelinformationservice.services.HotelService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/hotel")
public class HotelController{

    @Inject
    private HotelService service;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<IHotel> addNewHotel(@RequestBody Hotel hotel)
    {
        IHotel response = service.addNewHotel(hotel);
        URI uri = URI.create(String.format("/hotel/%s", response.getHotelId()));
        return ResponseEntity.created(uri).body(response);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Reservation> reservationRequest(@RequestBody Reservation reservation, @RequestParam("hotelId") Long hotelId){
        return ResponseEntity.ok(service.reservationRequest(hotelId, reservation)) ;
    }

    @RequestMapping(value = "/cancelReservation", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Reservation> cancelReservation(@RequestParam("hotelId") Long hotelId, @RequestParam("reservationId") Long reservationId){
        return ResponseEntity.accepted().body(service.cancelReservation(hotelId, reservationId));
    }

    @RequestMapping(value = "/reservationsByHotel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<List<Reservation>> getAllReservationsByHotelId(@RequestParam("hotelId") Long hotelId){
        return ResponseEntity.ok(service.getAllReservationsByHotelId(hotelId));
    }

    @RequestMapping(value = "/reservationsByGuest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public List<Reservation> getReservationByGuest(@RequestParam("hotelId") Long hotelId, @RequestParam("guestId") Long guestId)
    {
        List<Reservation> reservations = service.getReservationByGuestIdPerHotel(hotelId, guestId);
        return reservations;
    }

    @RequestMapping(value = "/confirmReservation" , method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Reservation> confirmReservation(@RequestParam("reservationId") Long reservationId){
        return ResponseEntity.accepted().body(service.confirmReservation(reservationId));
    }

    @RequestMapping(value = "/getHotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IHotel> getHotels(@RequestParam("hotelIds") List<Long> hotelIds){
        return service.getHotels(hotelIds);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public IHotel getHotelById(@RequestParam("hotelId") Long hotelId){
        return service.getHotelById(hotelId);
    }

}