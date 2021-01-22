package com.crs.microservices.hotelreservationservice.controller;

import com.crs.microservices.hotelreservationservice.exception.ReservationEntityNotFoundException;
import com.crs.microservices.hotelreservationservice.vo.Guest;
import com.crs.microservices.hotelreservationservice.vo.Reservation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * The Controller is consisting of the services to Store, retrieve and update Hotel Reservation.
 *
 * @author Rahul Khapre
 * @version 1.0
 * @since 2021-03-20
 */

@Api("APIs to store, retrieve and update reservation information.")
@RequestMapping("/reservations")
public interface ReservationController {

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Details stored successfully."),
            @ApiResponse(code = 401, message = "Authentication failure."),
            @ApiResponse(code = 403, message = "You are unauthorized to perform the operation."),
            @ApiResponse(code = 404, message = "Resource not found.")})

    /**
     * This method is used to create new Hotel.
     *
     * @param id This is the reservation id
     * used to fetch the reservation
     *
     * @return Reservation This returns Reservation Object.
     **/

    @ApiOperation(value = "This API is used to get reservation information by id.")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation getReservation(@PathVariable("id") Long id) throws Exception;

    /**
     * This method is used to create new Hotel.
     *
     * @param reservation This is the request body to make the reservation
     *
     * @return Reservation This returns created Reservation Object.
     **/

    @ApiOperation(value = "Make the reservation.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public Reservation reservationRequest(@RequestBody Reservation reservation) throws Exception;

    /**
     * This method is used to create new Hotel.
     *
     * @param reservation This is the request body to update/change the reservation
     *
     * @return Reservation This returns updated Reservation Object.
     **/

    @ApiOperation(value = "This API is used to Update reservation.")
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public Reservation updateReservation(@RequestBody Reservation reservation) throws ReservationEntityNotFoundException;

    /**
     * This method is used to create new Hotel.
     *
     * @param guestId This is the guest id to fetch the reservation on the basis of guest id
     *
     * @return Guest This returns Guest Object.
     **/

    @ApiOperation(value = "This API is used to Fetch reservation history for Guest")
    @GetMapping(value = "/guests/{guestId}")
    @PreAuthorize("hasRole('GUEST')")
    public Guest getReservationsByGuestId(@PathVariable("guestId") Long guestId) throws Exception;

}
