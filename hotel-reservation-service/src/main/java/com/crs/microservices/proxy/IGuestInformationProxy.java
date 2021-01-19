package com.crs.microservices.proxy;

import com.crs.microservices.proxy.model.guest.Guest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ZuulApiGateway")
public interface IGuestInformationProxy {
    @RequestMapping(value = "/GuestService/guest/{guestId}", method = RequestMethod.GET)
    public Guest getGuest(@PathVariable("guestId") Long guestId);

    @RequestMapping(value = "/GuestService/guest/addNewStay", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addStayByGuest(@RequestParam("guestId") Long guestId, @RequestParam("reservationId") Long stay);
}
