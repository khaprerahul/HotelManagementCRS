package com.crs.microservices.guestprofileservice.controller;

import com.crs.microservices.guestprofileservice.controller.GuestController;
import com.crs.microservices.guestprofileservice.vo.Card;
import com.crs.microservices.guestprofileservice.vo.Guest;
import com.crs.microservices.guestprofileservice.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;

@RestController
public class GuestControllerImpl implements GuestController {
    @Inject
    private GuestService service;

    @Override
    public ResponseEntity<Guest> addNewGuest(Guest guest) {
        Guest response = service.addNewGuest(guest);
        return ResponseEntity.created(URI.create(String.format("/guest/" + response.getGuestId())))
                .body(response);
    }

    @Override
    public ResponseEntity<Guest> getGuest(Long guestId) {
        Guest guest = service.getGuest(guestId);
        return ResponseEntity.ok(guest);
    }

    @Override
    public ResponseEntity<Guest> addStayByGuest(Long guestId, Long stay){
        return ResponseEntity.ok(service.addStayByGuest(guestId, stay));
    }

    @Override
    public ResponseEntity<List<Guest>> getGuests(List<Long> guestIds){
        return ResponseEntity.ok(service.getGuests(guestIds));
    }

}
