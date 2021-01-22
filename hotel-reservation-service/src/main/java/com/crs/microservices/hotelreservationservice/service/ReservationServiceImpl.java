package com.crs.microservices.hotelreservationservice.service;

import com.crs.microservices.hotelreservationservice.entity.ReservationEntity;
import com.crs.microservices.hotelreservationservice.exception.ReservationEntityNotFoundException;
import com.crs.microservices.hotelreservationservice.mapper.Mapper;
import com.crs.microservices.hotelreservationservice.proxy.model.guest.ProxyGuest;
import com.crs.microservices.hotelreservationservice.proxy.model.hotel.ProxyHotel;
import com.crs.microservices.hotelreservationservice.repository.ReservationRepositoryImpl;
import com.crs.microservices.hotelreservationservice.vo.Guest;
import com.crs.microservices.hotelreservationservice.vo.Reservation;
import com.crs.microservices.hotelreservationservice.proxy.HotelInformationProxy;
import com.crs.microservices.hotelreservationservice.vo.ReservationStatus;
import com.crs.microservices.hotelreservationservice.proxy.GuestInformationProxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private GuestInformationProxy guestProxy;

    @Autowired
    private HotelInformationProxy hotelProxy;

    @Autowired
    private ReservationRepositoryImpl reservationRepositoryImpl;

    @Inject
    private Mapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    public ProxyGuest getGuestById(Long guestId){
        ResponseEntity<ProxyGuest> guestResponseEntity = guestProxy.getGuest(guestId);
        return guestResponseEntity.getBody();
    }

    public ProxyHotel getHotelById(Long hotelId){
        return hotelProxy.getHotelById(hotelId);
    }

    public Reservation requestForReservation(Reservation reservation) throws Exception {
        ReservationEntity newReservation = reservationRepositoryImpl.save(mapper.mapIReservationToReservationDTO(reservation));
        hotelProxy.reservationRequest(mapper.mapReservationDTOToIReservation(newReservation), newReservation.getHotelId());
        return mapper.mapReservationDTOToIReservation(newReservation);
    }

    public Reservation updateReservation(Reservation reservation) throws ReservationEntityNotFoundException {
        ReservationEntity reservationEntity = reservationRepositoryImpl.getReservationById(reservation.getReservationId());
        if (reservationEntity != null) {
            reservationEntity.setState(reservation.getState().toString());
            hotelProxy.updateReservation(reservation.getHotelId(), reservation);
            if (reservation.getState().equals(ReservationStatus.CONFIRMED)) {
                guestProxy.addStayByGuest(reservation.getGuestId(), reservation.getReservationId());
                reservationEntity.setCard(mapper.mapICardToCardDTO(reservation.getCard()));
                guestProxy.addNewCard(reservationEntity.getGuestId(), reservation.getCard());
            }
            else if(reservation.getState().equals(ReservationStatus.CANCELLED)){
            }

            return mapper.mapReservationDTOToIReservation(reservationEntity);
        }
        else
        {
            throw new ReservationEntityNotFoundException("Reservation Entity with ID "+reservation.getReservationId()+" not found.");
        }
    }

    public Reservation getReservation(Long id) throws Exception {
         ReservationEntity reservationEntity = reservationRepositoryImpl.getReservationById(id);
         Reservation reservation = mapper.mapReservationDTOToIReservation(reservationEntity);
         return reservation;
    }

    @Override
    public Guest getReservationsByGuestId(Long guestId) {
        ProxyGuest guest = getGuestById(guestId);
        List<Reservation> reservationList = guest.getReservations().stream().map(reservationRepositoryImpl:: getReservationById)
                .map(mapper::mapReservationDTOToIReservation)
                .collect(Collectors.toList());
        Guest resultingGuest = mapper.mapProxyGuestToIGuest(guest);
        resultingGuest.setReservations(reservationList);
        return resultingGuest;
    }

}
