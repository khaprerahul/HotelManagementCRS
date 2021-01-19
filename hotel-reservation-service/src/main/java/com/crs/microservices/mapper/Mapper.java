package com.crs.microservices.mapper;

import com.crs.microservices.entity.CardDTO;
import com.crs.microservices.entity.ReservationDTO;
import com.crs.microservices.model.Card;
import com.crs.microservices.model.Reservation;

public interface Mapper {

    public ReservationDTO mapIReservationToReservationEntity(Reservation reservation);

    public Reservation mapReservationEntityToIReservation(ReservationDTO reservationDTO);

    public CardDTO mapICardToCardEntity(Card card);

}
