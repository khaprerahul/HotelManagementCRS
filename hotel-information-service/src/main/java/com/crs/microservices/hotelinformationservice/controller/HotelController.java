package com.crs.microservices.hotelinformationservice.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crs.microservices.hotelinformationservice.model.Hotel;
import com.crs.microservices.hotelinformationservice.model.IHotel;
import com.crs.microservices.hotelinformationservice.model.IReservation;
import com.crs.microservices.hotelinformationservice.services.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Inject
	private HotelService service;

	@PostMapping("/addHotel")
	@PreAuthorize("hasRole('HOTEL')")
	public ResponseEntity<IHotel> addNewHotel(@RequestBody Hotel hotel) {
		IHotel response = service.addNewHotel(hotel);
		URI uri = URI.create(String.format("/hotel/%s", response.getHotelId()));
		return ResponseEntity.created(uri).body(response);
	}

	@PostMapping("/reservation")
	@PreAuthorize("hasRole('HOTEL')")
	public String reservationRequest(@RequestBody IReservation reservation, @RequestParam("hotelId") Long hotelId) {
		return service.reservationRequest(hotelId, reservation);
	}

	@PatchMapping("/cancelReservation")
	public ResponseEntity<String> cancelReservation(@RequestParam("hotelId") Long hotelId,
			@RequestParam("reservationId") Long reservationId) {
		return ResponseEntity.accepted().body(service.cancelReservation(hotelId, reservationId));
	}

	@GetMapping("/reservationsByHotel")
	public ResponseEntity<List<IReservation>> getAllReservationsByHotelId(@RequestParam("hotelId") Long hotelId) {
		return ResponseEntity.ok(service.getAllReservationsByHotelId(hotelId));
	}

	@GetMapping("/reservationsByGuest")
	public List<IReservation> getReservationByGuest(@RequestParam("hotelId") Long hotelId,
			@RequestParam("guestId") Long guestId) {
		return service.getReservationByGuestIdPerHotel(hotelId, guestId);
	}

	@PatchMapping("/confirmReservation")
	public String confirmReservation(@RequestParam("reservationId") Long reservationId) {
		return service.confirmReservation(reservationId);
	}

	@GetMapping("/getHotels")
	public List<IHotel> getHotels(@RequestParam("hotelIds") List<Long> hotelIds) {
		return service.getHotels(hotelIds);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public IHotel getHotelById(@RequestParam("hotelId") Long hotelId) {
		return service.getHotelById(hotelId);
	}

	@GetMapping("/searchHotels")
	public List<IHotel> searchHotels(@RequestParam String cityName, @RequestParam Date fromDate,
			@RequestParam Date toDate, String roomType) {
		return service.searchHotels(cityName, fromDate, toDate, roomType);
	}
}
