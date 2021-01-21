package com.crs.microservices.hotelinformationservice.controller;

import com.crs.microservices.hotelinformationservice.vo.Hotel;
import com.crs.microservices.hotelinformationservice.vo.IHotel;
import com.crs.microservices.hotelinformationservice.vo.Reservation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Controller is consisting of the services to Store, retrieve and update Hotel information.
 *
 * @author Rahul Khapre
 * @version 1.0
 * @since 2021-03-20
 */

@Api(value = " API's to Store, retrieve and update Hotel information.")
public interface HotelController {

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Hotel information stored successfully."),
            @ApiResponse(code = 401, message = "Authentication failure."),
            @ApiResponse(code = 403, message = "You are unauthorized to perform the operation."),
            @ApiResponse(code = 404, message = "Resource not found.")})


    /**
     * This method is used to create new Hotel.
     *
     * @param hotel This is the request body paramter
     * to create the Hotel
     *
     * @return ResponseEntity<IHotel> This returns created Guest Object.
     **/

    @ApiOperation(value = "This API is to Add new Hotel.")
    @PostMapping(value = "/hotels", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<IHotel> addNewHotel(@RequestBody Hotel hotel);

    /**
     * This method is used to create new Hotel.
     *
     * @param reservation This method is associate reservation request for a particular hotel
     * @param hotelId This is the second paramter
     * to associate the reservation with the Hotel
     *
     * @return ResponseEntity<Reservation> This returns created Reservation Object.
     **/

    @ApiOperation(value = "This API is associate reservation request for a particular hotel.")
    @PostMapping(value = "/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Reservation> reservationRequest(@RequestBody Reservation reservation, @PathVariable("hotelId") Long hotelId);

    /**
     * This method is used to change or update the reservation.
     *
     * @param reservation This is the request body paramter
     * @param hotelId This is the second paramter
     * to associate the reservation with the Hotel
     *
     * @return ResponseEntity<Reservation> This returns created Reservation Object.
     **/

    @ApiOperation(value = "This API is to change or update reservation.")
    @PutMapping(value = "/hotels/{hotelId}/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("hotelId") Long hotelId, @RequestBody Reservation reservation);

    /**
     * This method is to Fetch all reservations based on hotel id.
     *
     * @param hotelId This paramter to fetch associated reservations with one Hotel
     *
     *
     * @return ResponseEntity<List<Reservation>> This returns created Reservation Object.
     **/

    @ApiOperation(value = "This API is to Fetch all reservations based on hotel id.")
    @GetMapping(value = "/hotels/{hotelId}/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public ResponseEntity<List<Reservation>> getAllReservationsByHotelId(@PathVariable("hotelId") Long hotelId);

    /**
     * This method is to Fetch all reservations based on hotel id.
     *
     * @param hotelId This is the request body paramter
     *
     * @return ResponseEntity<List<Reservation>> This returns created Reservation Object.
     **/

    @ApiOperation(value = "This API is to Fetch all reservations for guest based on hotel id.")
    @GetMapping(value = "/hotels/{hotelId}/{guestId}/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('HOTEL')")
    public List<Reservation> getReservationByGuestId(@PathVariable("hotelId") Long hotelId, @PathVariable("guestId") Long guestId);

    /**
     * This method is to Fetch all hotel information based on list of hotel ids.
     *
     * @param hotelIds This is the list of the hotelIds
     *
     * @return List<IHotel> This returns List of Hotels.
     **/

    @ApiOperation(value = "This API is to fetch hotel information based on list of hotel ids.")
    @GetMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole( 'GUEST')")
    public List<IHotel> getListOfHotels(@RequestParam("hotelIds") List<Long> hotelIds);

    /**
     * This method is to Fetch all hotel information based on list of hotel ids.
     *
     * @param hotelId This is the parameter hotelId
     *
     * @return IHotel This returns Hotel Object.
     **/

    @ApiOperation(value = "This API is to Fetch hotel info based on hotel id.")
    @GetMapping(value = "/hotels/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole( 'GUEST')")
    public IHotel getHotelById(@PathVariable("hotelId") Long hotelId);

}
