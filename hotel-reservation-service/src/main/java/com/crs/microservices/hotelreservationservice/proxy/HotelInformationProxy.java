package com.crs.microservices.hotelreservationservice.proxy;

import com.crs.microservices.hotelreservationservice.proxy.model.hotel.ProxyHotel;
import com.crs.microservices.hotelreservationservice.vo.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "netflix-zuul-api-gateway-server")
public interface HotelInformationProxy {
    @GetMapping(value = "/hotel-information-service/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProxyHotel> getHotels(@RequestParam("hotelIds") List<Long> hotelIds);

    @GetMapping(value = "/hotel-information-service/hotels/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProxyHotel getHotelById(@PathVariable("hotelId") Long hotelId);

    @PostMapping(value = "/hotel-information-service/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> reservationRequest(@RequestBody Reservation reservation, @PathVariable("hotelId") Long hotelId);

    @PutMapping(value = "/hotel-information-service/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> updateReservation(@PathVariable("hotelId") Long hotelId, @RequestBody Reservation reservation);
}
