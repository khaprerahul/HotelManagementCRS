package com.crs.microservices.guestprofileservice.services;

import java.util.List;

import com.crs.microservices.guestprofileservice.model.IGuest;

public interface GuestService {
	public IGuest addNewGuest(IGuest guest);

	public IGuest getGuest(Long id);

	public String addStayByGuest(Long guestId, Long stay);

	public List<IGuest> getGuests(List<Long> guestIds);
}
