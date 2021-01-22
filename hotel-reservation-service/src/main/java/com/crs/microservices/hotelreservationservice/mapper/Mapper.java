package com.crs.microservices.hotelreservationservice.mapper;

import com.crs.microservices.hotelreservationservice.proxy.model.guest.ProxyGuest;
import com.crs.microservices.hotelreservationservice.vo.Card;
import com.crs.microservices.hotelreservationservice.vo.Guest;
import com.crs.microservices.hotelreservationservice.proxy.model.card.ICard;
import com.crs.microservices.hotelreservationservice.entity.CardEntity;
import com.crs.microservices.hotelreservationservice.entity.ReservationEntity;
import com.crs.microservices.hotelreservationservice.vo.Reservation;

public interface Mapper {

    public ReservationEntity mapIReservationToReservationDTO(Reservation reservation);

    public Reservation mapReservationDTOToIReservation(ReservationEntity reservationEntity);

    public CardEntity mapICardToCardDTO(Card card);

    ICard mapICardToProxy(Card card);

    public Card cardDTOToICard(CardEntity card);

    Guest mapProxyGuestToIGuest(ProxyGuest body);
}
