package com.crs.microservices.hotelreservationservice.proxy;

import com.crs.microservices.hotelreservationservice.vo.Card;
import com.crs.microservices.hotelreservationservice.proxy.model.guest.ProxyGuest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "netflix-zuul-api-gateway-server")
public interface GuestInformationProxy {
    @GetMapping(value = "/guest-profile-service/guests/{guestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProxyGuest> getGuest(@PathVariable("guestId") Long guestId);

    @PutMapping(value = "/guest-profile-service/guests/{guestId}/stay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProxyGuest> addStayByGuest(@PathVariable("guestId") Long guestId, @RequestParam("reservationId") Long stay);

    @PostMapping(value = "/guest-profile-service/guests/{guestId}/card", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProxyGuest> addNewCard(@PathVariable("guestId") Long guestId, @RequestBody Card card);
}
