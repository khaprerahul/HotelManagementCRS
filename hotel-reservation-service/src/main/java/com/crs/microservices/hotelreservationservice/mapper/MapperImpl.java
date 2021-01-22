package com.crs.microservices.hotelreservationservice.mapper;

import com.crs.microservices.hotelreservationservice.entity.CardEntity;
import com.crs.microservices.hotelreservationservice.entity.ReservationEntity;
import com.crs.microservices.hotelreservationservice.proxy.model.guest.ProxyGuest;
import com.crs.microservices.hotelreservationservice.vo.*;
import com.crs.microservices.hotelreservationservice.proxy.model.card.ICard;
import com.crs.microservices.hotelreservationservice.proxy.model.card.implementation.Card;

public class MapperImpl implements Mapper {
    public ReservationEntity mapIReservationToReservationDTO(Reservation reservation){
        ReservationEntity reservationEntity =  new ReservationEntity();
        reservationEntity.setReservationId(reservation.getReservationId());
        reservationEntity.setFromDate(reservation.getFromDate());
        reservationEntity.setGuestId(reservation.getGuestId());
        reservationEntity.setState(reservation.getState().toString());
        reservationEntity.setHotelId(reservation.getHotelId());
        reservationEntity.setToDate(reservation.getToDate());
        reservationEntity.setCard(reservation.getCard()!= null ? mapICardToCardDTO(reservation.getCard()): null);
        reservationEntity.setAmount(reservation.getAmount());
        return reservationEntity;
    }

    public Reservation mapReservationDTOToIReservation(ReservationEntity reservationEntity){
        Reservation reservation =  new ReservationVO();
        reservation.setReservationId(reservationEntity.getReservationId());
        reservation.setFromDate(reservationEntity.getFromDate());
        reservation.setGuestId(reservationEntity.getGuestId());
        reservation.setHotelId(reservationEntity.getHotelId());
        reservation.setState(ReservationStatus.valueOf(reservationEntity.getState()));
        reservation.setToDate(reservationEntity.getToDate());
        reservation.setCard(reservationEntity.getCard() != null ? mapCardDTOToICard(reservationEntity.getCard()): null);
        reservation.setAmount(reservationEntity.getAmount());
        return reservation;
    }

    private com.crs.microservices.hotelreservationservice.vo.Card mapCardDTOToICard(CardEntity card) {
        com.crs.microservices.hotelreservationservice.vo.Card iCard =  new CardVO(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return iCard;
    }

    @Override
    public CardEntity mapICardToCardDTO(com.crs.microservices.hotelreservationservice.vo.Card card) {
        CardEntity cardEntity =  new CardEntity(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return cardEntity;
    }

    @Override
    public ICard mapICardToProxy(com.crs.microservices.hotelreservationservice.vo.Card card) {
        ICard proxyCard =  new Card(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return proxyCard;
    }

    @Override
    public com.crs.microservices.hotelreservationservice.vo.Card cardDTOToICard(CardEntity card) {
        com.crs.microservices.hotelreservationservice.vo.Card iCard =  new CardVO(card.getCardNumber(), card.getExpMonth(), card.getExpYear());
        return iCard;
    }

    @Override
    public Guest mapProxyGuestToIGuest(ProxyGuest proxyGuest) {
        Guest guest =  new GuestVO();
        guest.setGuestId(proxyGuest.getGuestId());
        guest.setContactNumber(proxyGuest.getContactNumber());
        guest.setEmail(proxyGuest.getEmail());
        guest.setName(proxyGuest.getName());
        return guest;
    }
}
