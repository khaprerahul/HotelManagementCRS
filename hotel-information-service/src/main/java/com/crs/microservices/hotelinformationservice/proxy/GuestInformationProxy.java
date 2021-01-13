package com.crs.microservices.hotelinformationservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.crs.microservices.hotelinformationservice.model.IGuest;

@FeignClient(name = "netflix-zuul-api-gateway-server")
public interface GuestInformationProxy {

	@GetMapping("/guest-profile-service/guest/{guestId}")
	public ResponseEntity<IGuest> getGuest(@PathVariable("guestId") Long guestId);

}
