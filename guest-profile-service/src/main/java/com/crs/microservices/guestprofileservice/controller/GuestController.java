package com.crs.microservices.guestprofileservice.controller;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crs.microservices.guestprofileservice.model.IGuest;
import com.crs.microservices.guestprofileservice.services.GuestService;

@RestController
@RequestMapping("/guest")
public class GuestController {
	@Inject
	private GuestService service;

	@PostMapping("/createGuest")
	@PreAuthorize("hasRole('GUEST')")
	public ResponseEntity<IGuest> addNewGuest(@RequestBody IGuest guest) {
		IGuest response = service.addNewGuest(guest);
		return ResponseEntity.created(URI.create(String.format("/guest/" + response.getGuestId()))).body(response);
	}

	@GetMapping("/{guestId}")
	public ResponseEntity<IGuest> getGuest(@PathVariable("guestId") Long guestId) {
		IGuest guest = service.getGuest(guestId);
		return ResponseEntity.ok(guest);
	}

	@PatchMapping("/addNewStay")
	public ResponseEntity<String> addStayByGuest(@RequestParam("guestId") Long guestId,
			@RequestParam("reservationId") Long stay) {
		return ResponseEntity.ok(service.addStayByGuest(guestId, stay));
	}

	@GetMapping("/getGuests")
	public ResponseEntity<List<IGuest>> getGuests(List<Long> guestIds) {
		return ResponseEntity.ok(service.getGuests(guestIds));
	}
}
