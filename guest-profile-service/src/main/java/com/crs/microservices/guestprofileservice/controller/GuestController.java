package com.crs.microservices.guestprofileservice.controller;

import com.crs.microservices.guestprofileservice.model.Card;
import com.crs.microservices.guestprofileservice.model.Guest;
import com.crs.microservices.guestprofileservice.service.GuestService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {
    @Inject
    private GuestService service;


    @PostMapping("/create")
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Guest> addNewGuest(@RequestBody Guest guest) {
        Guest response = service.addNewGuest(guest);
        return ResponseEntity.created(URI.create(String.format("/guest/" + response.getGuestId())))
                .body(response);
    }

    @GetMapping("/{guestId}")
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Guest> getGuest(@PathVariable("guestId") Long guestId) {
        Guest guest = service.getGuest(guestId);
        return ResponseEntity.ok(guest);
    }

    @PatchMapping("/addNewStay")
    @PreAuthorize("hasRole('GUEST')")
    public ResponseEntity<Guest> addStayByGuest(@RequestParam("guestId") Long guestId, @RequestParam("reservationId") Long stay){
        return ResponseEntity.ok(service.addStayByGuest(guestId, stay));
    }

    @GetMapping("/getGuests")
    public ResponseEntity<List<Guest>> getGuests(@RequestParam("guestId") List<Long> guestIds){
        return ResponseEntity.ok(service.getGuests(guestIds));
    }

}
