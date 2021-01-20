package com.crs.microservices.guestprofileservice.mapper;

import com.crs.microservices.guestprofileservice.entity.CardEntity;
import com.crs.microservices.guestprofileservice.entity.GuestEntity;
import com.crs.microservices.guestprofileservice.model.Card;
import com.crs.microservices.guestprofileservice.model.Guest;

public interface Mapper {
    public Guest mapGuestEntityToGuest(GuestEntity guestEntity);

    public GuestEntity mapGuestToGuestEntity(Guest guest);

    public CardEntity mapCardToCardEntity(Card card);
}
