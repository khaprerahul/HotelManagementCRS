package com.crs.microservices.guestprofileservice.service;

import com.crs.microservices.guestprofileservice.vo.Card;
import com.crs.microservices.guestprofileservice.vo.Guest;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface GuestService {

    public Guest addNewGuest(Guest guest);

    public Guest getGuest(Long id) throws EntityNotFoundException;

    public Guest addStayByGuest(Long guestId, Long stay) throws EntityNotFoundException;

    public List<Guest> getGuests(List<Long> guestIds ) throws EntityNotFoundException;

}
