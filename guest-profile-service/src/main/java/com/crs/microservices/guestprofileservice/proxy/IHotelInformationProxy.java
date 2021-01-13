package com.crs.microservices.guestprofileservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crs.microservices.guestprofileservice.model.IHotel;

import org.springframework.http.MediaType;


@FeignClient(name = "netflix-zuul-api-gateway-server")
public interface IHotelInformationProxy {
	 @RequestMapping(value = "/HotelService/hotel/getHotels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<IHotel> getHotels(@RequestParam List<Long> hotelIds);
}
