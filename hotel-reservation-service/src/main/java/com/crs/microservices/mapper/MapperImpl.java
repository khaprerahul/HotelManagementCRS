package com.crs.microservices.mapper;

import com.crs.microservices.entity.CardDTO;
import com.crs.microservices.entity.ReservationDTO;
import com.crs.microservices.model.CardImpl;
import com.crs.microservices.model.Reservation;
import com.crs.microservices.model.ReservationImpl;

public class MapperImpl implements Mapper {
    public ReservationDTO mapIReservationToReservationEntity(Reservation reservation){
        ReservationDTO reservationDTO =  new ReservationDTO();
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setFromDate(reservation.getFromDate());
        reservationDTO.setGuestId(reservation.getGuestId());
        reservationDTO.setState(reservation.getState());
        reservationDTO.setHotelId(reservation.getHotelId());
        reservationDTO.setToDate(reservation.getToDate());
        reservationDTO.setCard(mapICardToCardEntity(reservation.getCard()));
        return reservationDTO;
    }

    public Reservation mapReservationEntityToIReservation(ReservationDTO reservationDTO){
        Reservation reservation =  new ReservationImpl();
        reservation.setReservationId(reservationDTO.getReservationId());
        reservation.setFromDate(reservationDTO.getFromDate());
        reservation.setGuestId(reservationDTO.getGuestId());
        reservation.setHotelId(reservationDTO.getHotelId());
        reservation.setState(reservationDTO.getState());
        reservation.setToDate(reservationDTO.getToDate());
        reservation.setCard(mapCardDTOToICard(reservationDTO.getCard()));
        return reservation;
    }

    private com.crs.microservices.model.Card mapCardDTOToICard(CardDTO card) {
        com.crs.microservices.model.Card iCard =  new CardImpl(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return iCard;
    }

    @Override
    public CardDTO mapICardToCardEntity(com.crs.microservices.model.Card card) {
        CardDTO cardDTO =  new CardDTO(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return cardDTO;
    }

}
