package com.crs.microservices.service;

import com.crs.microservices.entity.ReservationDTO;
import com.crs.microservices.mapper.Mapper;
import com.crs.microservices.model.Reservation;
import com.crs.microservices.proxy.IGuestInformationProxy;
import com.crs.microservices.proxy.IHotelInformationProxy;
import com.crs.microservices.proxy.model.guest.Guest;
import com.crs.microservices.proxy.model.hotel.Hotel;
import com.crs.microservices.repository.implementation.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;

public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private IGuestInformationProxy guestProxy;

    @Autowired
    private IHotelInformationProxy hotelProxy;

    @Autowired
    private ReservationRepository reservationRepository;

    @Inject
    private Mapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    public Guest getGuestById(Long guestId) {
        return guestProxy.getGuest(guestId);
    }

    public Hotel getHotelById(Long hotelId) {
        return hotelProxy.getHotelById(hotelId);
    }

    public String requestForReservation(Reservation reservation) {
        ReservationDTO reservationDTO = mapper.mapIReservationToReservationEntity(reservation);
        ReservationDTO newReservation = reservationRepository.save(reservationDTO);
        return hotelProxy.reservationRequest(mapper.mapReservationEntityToIReservation(newReservation), reservation.getHotelId());
    }

    public Reservation confirmReservation(Reservation reservation) {
        hotelProxy.confirmReservation(reservation.getReservationId());
        guestProxy.addStayByGuest(reservation.getGuestId(), reservation.getReservationId());
        ReservationDTO reservationDTO = reservationRepository.getReservationById(reservation.getReservationId());
        reservationDTO.setState("CONFIRMED");
        return mapper.mapReservationEntityToIReservation(reservationDTO);
    }

    public Reservation getReservation(Long id, boolean isDetailsRequired) {
        ReservationDTO reservationDTO = reservationRepository.getReservationById(id);
        Reservation reservation = mapper.mapReservationEntityToIReservation(reservationDTO);
         /*if (isDetailsRequired){
             Hotel hotel = getHotelById(reservationDTO.getHotelId());
             Guest guest = getGuestById(reservationDTO.getGuestId());
             reservation.setHotel(hotel);
             reservation.setGuest(guest);
         }*/
        return reservation;
    }

    @Override
    public Reservation cancelReservation(Long id, double amount) throws Exception {
        ReservationDTO reservationDTO = reservationRepository.getReservationById(id);
        ResponseEntity<Reservation> cancelReservation = hotelProxy.cancelReservation(reservationDTO.getHotelId(), reservationDTO.getReservationId());
        if (cancelReservation.getStatusCode().is2xxSuccessful()) {
            reservationDTO.setState("CANCELLED");
        } else {
            throw new Exception("Request get rejected by Hotel.");
        }
        return mapper.mapReservationEntityToIReservation(reservationDTO);
    }
}
