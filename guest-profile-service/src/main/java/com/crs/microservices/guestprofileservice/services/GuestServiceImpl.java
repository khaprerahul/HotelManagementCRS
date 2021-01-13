package com.crs.microservices.guestprofileservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.crs.microservices.guestprofileservice.dto.GuestDTO;
import com.crs.microservices.guestprofileservice.mapper.IMapper;
import com.crs.microservices.guestprofileservice.model.IGuest;
import com.crs.microservices.guestprofileservice.repository.GuestRepository;

public class GuestServiceImpl implements GuestService {
	private final Logger logger = LoggerFactory.getLogger(GuestServiceImpl.class);

	@Autowired
	private GuestRepository repository;

	@Inject
	private IMapper mapper;

	public IGuest addNewGuest(IGuest guest) {
		logger.debug("Request to add new guest.{}", guest);
		GuestDTO savedGuest = repository.save(mapper.mapIGuestToGuestDTO(guest));
		return mapper.mapGuestDTOToIGuest(savedGuest);
	}

	public IGuest getGuest(Long id) {
		Optional<GuestDTO> guest = repository.findById(id);
		if (guest.isPresent()) {
			return mapper.mapGuestDTOToIGuest(guest.get());
		}

		return null;
	}

	// @Transactional(Transactional.TxType.REQUIRES_NEW)
	public String addStayByGuest(Long guestId, Long reservationId) {
		Optional<GuestDTO> guest = repository.findById(guestId);
		if (guest.isPresent()) {
			guest.get().getReservations().add(reservationId);
			return "Stay information stored.";
		}
		return "Guest information is not found.";
	}

	public List<IGuest> getGuests(List<Long> guestIds) {
		List<IGuest> guests = new ArrayList<>();
		for (Long guestId : guestIds) {
			IGuest guest = getGuest(guestId);
			guests.add(guest);
		}
		return guests;
	}
}
