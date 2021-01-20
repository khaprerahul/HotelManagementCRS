package com.crs.microservices.guestprofileservice.service;

import com.crs.microservices.guestprofileservice.entity.GuestEntity;
import com.crs.microservices.guestprofileservice.mapper.Mapper;
import com.crs.microservices.guestprofileservice.model.Card;
import com.crs.microservices.guestprofileservice.model.Guest;
import com.crs.microservices.guestprofileservice.repository.GuestRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class GuestServiceImpl implements GuestService {

    final private Logger LOGGER = LoggerFactory.getLogger(GuestServiceImpl.class);

    @Autowired
    private GuestRepositoryImpl repository;

    @Inject
    private Mapper mapper;

    public Guest addNewGuest(Guest guest) {
        LOGGER.debug("GuestService :: getGuest :: Request to add new guest.{}"+guest);
        GuestEntity savedGuest = repository.save(mapper.mapGuestToGuestEntity(guest));
        return mapper.mapGuestEntityToGuest(savedGuest);
    }

    public Guest getGuest(Long id) throws EntityNotFoundException {
        LOGGER.debug("GuestService :: getGuest :: Request to fetch guest with id "+id);
        GuestEntity guest = repository.findById(id);
        Guest guest1 = mapper.mapGuestEntityToGuest(guest);
        LOGGER.debug("GuestService :: getGuest :: Guest information returned "+guest1);
        return guest1;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Guest addStayByGuest(Long guestId, Long reservationId) throws EntityNotFoundException {
        LOGGER.debug("GuestService :: addStayByGuest :: Guest requested for new stay, Guest ID "+guestId+" , stay ID "+reservationId);
        GuestEntity guest = repository.findById(guestId);
        guest.getReservations().add(reservationId);
        return mapper.mapGuestEntityToGuest(guest);
    }

    public List<Guest> getGuests(List<Long> guestIds ) throws EntityNotFoundException {
        List<Guest> guests = new ArrayList();
        for(Long guestId : guestIds){
            Guest guest = getGuest(guestId);
            guests.add(guest);
        }
        return guests;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Guest addNewCard(long guestId, Card card) {
        GuestEntity guestEntity = repository.findById(guestId);
        guestEntity.getCards().add(mapper.mapCardToCardEntity(card));
        return mapper.mapGuestEntityToGuest(guestEntity);
    }
}
