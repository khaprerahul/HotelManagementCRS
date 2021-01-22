package com.crs.microservices.hotelreservationservice.controller;

import com.crs.microservices.hotelreservationservice.exception.ReservationEntityNotFoundException;
import com.crs.microservices.hotelreservationservice.service.ReservationService;
import com.crs.microservices.hotelreservationservice.vo.Guest;
import com.crs.microservices.hotelreservationservice.vo.Reservation;

import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
public class ReservationControllerImpl implements ReservationController {

    @Inject
    private ReservationService reservationService;

    @Override
    public Reservation getReservation(Long id) throws Exception {
        return reservationService.getReservation(id);
    }

    @Override
    public Reservation reservationRequest(Reservation reservation) throws Exception {
        return reservationService.requestForReservation(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) throws ReservationEntityNotFoundException {
        return reservationService.updateReservation(reservation);
    }

    @Override
    public Guest getReservationsByGuestId(Long guestId) throws Exception {
        return reservationService.getReservationsByGuestId(guestId);
    }
}
