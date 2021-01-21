package com.crs.microservices.guestprofileservice.mapper;

import com.crs.microservices.guestprofileservice.entity.CardEntity;
import com.crs.microservices.guestprofileservice.entity.GuestEntity;
import com.crs.microservices.guestprofileservice.vo.Card;
import com.crs.microservices.guestprofileservice.vo.Guest;

public interface Mapper {
    public Guest mapGuestDTOToIGuest(GuestEntity guestEntity);

    public GuestEntity mapIGuestToGuestDTO(Guest guest);

    public CardEntity mapICardToCardDTO(Card card);
}
