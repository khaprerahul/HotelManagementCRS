package com.crs.microservices.guestprofileservice.controller;

import com.crs.microservices.guestprofileservice.vo.Card;
import com.crs.microservices.guestprofileservice.vo.Guest;
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
 * The Controller is consisting of the services for storing, retrieval and updating Guest Profile
 *
 * @author Rahul Khapre
 * @version 1.0
 * @since 2021-03-20
 */

@Api(value = "Guest Information", description = "Services for storing, retrieval and updating Guest Profile")
@RequestMapping("/guests")
public interface GuestController {

    @ApiOperation(value = "Guest information Service.", response = Guest.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Guest information stored successfully."),
            @ApiResponse(code = 401, message = "Authentication failure."),
            @ApiResponse(code = 403, message = "Unauthorized to perform the operation."),
            @ApiResponse(code = 404, message = "Resource not found.")

    })

    /**
     * This method is used to add new Guest profile.This is
     *
     * @param guest This is the request body paramter
     * to create the Guest Profile
     *
     * @return ResponseEntity<Guest> This returns created Guest Object.
     **/

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Guest> addNewGuest(@RequestBody Guest guest);

    /**
     * This method is used to Fetch Guest profile based on ID.
     *
     * @param guestId This is the parameter which is used to fetch the guest
     * @return ResponseEntity<Guest> This returns the Guest Object with specific ID.
     **/

    @ApiOperation(value = "Fetch Information of Guest Based on ID.")
    @GetMapping(value = "/{guestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Guest> getGuest(@PathVariable("guestId") Long guestId);

    /**
     * This method is used to Update Guest Stay Information for stay history
     *
     * @param guestId This is the parameter which is used to update the stay
     * @param stay    This is the parameter reservationID which is used to fetch the guest
     * @return ResponseEntity<Guest> This returns the Guest Object with specific ID.
     **/

    @ApiOperation(value = "Update Guest Stay Information for stay history.")
    @PutMapping(value = "/{guestId}/stay", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Guest> addStayByGuest(@PathVariable("guestId") Long guestId, @RequestParam("reservationId") Long stay);

    /**
     * This method is used to Fetch the list of Guest based on ID
     *
     * @param guestIds This is the parameter which signifies list of guest ID's
     * @return ResponseEntity<List < Guest>> This returns the List of Guest Objects with specific ID.
     **/

    @ApiOperation(value = "Fetch the Information for multiple guests based on list of ID's.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Guest>> getGuests(@RequestParam("guestId") List<Long> guestIds);

}
