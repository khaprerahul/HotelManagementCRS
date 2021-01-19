package com.crs.microservices.controller;

import com.crs.microservices.model.Card;
import com.crs.microservices.model.Reservation;

import com.crs.microservices.service.ReservationService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Inject
    private ReservationService reservationService;

    @GetMapping("/get")
    public Reservation getReservation(@RequestParam("id") Long id, @RequestParam("isDetailsRequired") boolean isDetailsRequired) {
        return reservationService.getReservation(id, isDetailsRequired);
    }

    @PostMapping("/request")
    @PreAuthorize("hasRole('GUEST')")
    public String reservationRequest(@RequestBody Reservation reservation) {
        return reservationService.requestForReservation(reservation);
    }

    @PatchMapping("/confirm")
    @PreAuthorize("hasRole('GUEST')")
    public Reservation confirmReservation(@RequestBody Reservation reservation) {
        return reservationService.confirmReservation(reservation);
    }

    @PatchMapping("/cancel")
    public Reservation cancelReservation(@RequestParam("reservationId") Long id, @RequestParam("amount") double amount) throws Exception {

        return reservationService.cancelReservation(id, amount);
    }

}
