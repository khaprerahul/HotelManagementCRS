package com.crs.microservices.guestprofileservice.mapper;

import com.crs.microservices.guestprofileservice.entity.CardEntity;
import com.crs.microservices.guestprofileservice.entity.GuestEntity;
import com.crs.microservices.guestprofileservice.vo.Card;
import com.crs.microservices.guestprofileservice.vo.Guest;
import com.crs.microservices.guestprofileservice.vo.CardImpl;
import com.crs.microservices.guestprofileservice.vo.GuestImpl;

import java.util.stream.Collectors;

public class MapperImpl implements Mapper {
    @Override
    public Guest mapGuestDTOToIGuest(GuestEntity guestEntity) {
        Guest guest =  new GuestImpl();
        guest.setGuestId(guestEntity.getGuestId());
        guest.setContactNumber(guestEntity.getContactNumber());
        guest.setEmail(guestEntity.getEmail());
        guest.setName(guestEntity.getName());
        guest.setRating(guestEntity.getRatting());
        guest.setCards(guestEntity.getCards().stream().map(this::mapCardDTOToICard).collect(Collectors.toList()));
        guest.setReservations(guestEntity.getReservations());
        return guest;
    }

    @Override
    public GuestEntity mapIGuestToGuestDTO(Guest guest) {
        GuestEntity guestEntity =  new GuestEntity();
        guestEntity.setGuestId(guest.getGuestId());
        guestEntity.setName(guest.getName());
        guestEntity.setEmail(guest.getEmail());
        guestEntity.setRatting(guest.getRating());
        guestEntity.setContactNumber(guest.getContactNumber());
        guestEntity.setCards(guest.getCards().stream().map(this::mapICardToCardDTO).collect(Collectors.toList()));
        guestEntity.setReservations(guest.getReservations());
        return guestEntity;
    }

    public Card mapCardDTOToICard(CardEntity cardEntity){
        Card card = new CardImpl();
        card.setCardNumber(cardEntity.getCardNumber());
        card.setExpMonth(cardEntity.getExpMonth());
        card.setExpYear(cardEntity.getExpYear());
        return card;
    }

    public CardEntity mapICardToCardDTO(Card card){
        CardEntity cardEntity =  new CardEntity();
        cardEntity.setCardNumber(card.getCardNumber());
        cardEntity.setExpMonth(card.getExpMonth());
        cardEntity.setExpYear(card.getExpYear());
        return cardEntity;
    }
}
