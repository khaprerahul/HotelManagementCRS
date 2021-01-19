package com.crs.microservices.proxy;

import com.crs.microservices.model.Reservation;
import com.crs.microservices.proxy.model.hotel.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ZuulApiGateway")
public interface IHotelInformationProxy {
    @RequestMapping(value = "/HotelService/hotel/getHotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hotel> getHotels(@RequestParam List<Long> hotelIds);

    @RequestMapping(value = "/HotelService/hotel/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel getHotelById(@RequestParam("hotelId") Long hotelId);

    @RequestMapping(method = RequestMethod.POST, value = "/HotelService/hotel/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String reservationRequest(@RequestBody Reservation reservation, @RequestParam("hotelId") Long hotelId);

    @RequestMapping(value = "/HotelService/hotel/confirmReservation" , method = RequestMethod.PATCH)
    public String confirmReservation(@RequestParam("reservationId") Long reservationId);

    @RequestMapping(value = "/HotelService/hotel/cancelReservation", method = RequestMethod.PATCH)
    public ResponseEntity<Reservation> cancelReservation(@RequestParam("hotelId") Long hotelId, @RequestParam("reservationId") Long reservationId);
}
