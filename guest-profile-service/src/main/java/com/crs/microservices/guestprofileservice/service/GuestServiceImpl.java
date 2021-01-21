package com.crs.microservices.guestprofileservice.service;

import com.crs.microservices.guestprofileservice.mapper.Mapper;
import com.crs.microservices.guestprofileservice.vo.Card;
import com.crs.microservices.guestprofileservice.vo.Guest;
import com.crs.microservices.guestprofileservice.repository.GuestRepositoryImpl;
import com.crs.microservices.guestprofileservice.entity.GuestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class GuestServiceImpl implements GuestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuestServiceImpl.class);

    @Autowired
    private GuestRepositoryImpl repository;

    @Inject
    private Mapper mapper;

    public Guest addNewGuest(Guest guest) {
        GuestEntity savedGuest = repository.save(mapper.mapIGuestToGuestDTO(guest));
        return mapper.mapGuestDTOToIGuest(savedGuest);
    }

    public Guest getGuest(Long id) throws EntityNotFoundException {
        GuestEntity guest = repository.findById(id);
        Guest guest1 = mapper.mapGuestDTOToIGuest(guest);
        return guest1;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Guest addStayByGuest(Long guestId, Long reservationId) throws EntityNotFoundException {
        GuestEntity guest = repository.findById(guestId);
        guest.getReservations().add(reservationId);
        return mapper.mapGuestDTOToIGuest(guest);
    }

    public List<Guest> getGuests(List<Long> guestIds ) throws EntityNotFoundException {
        List<Guest> guests = new ArrayList<>();
        for(Long guestId : guestIds){
            Guest guest = getGuest(guestId);
            guests.add(guest);
        }
        return guests;
    }
}
