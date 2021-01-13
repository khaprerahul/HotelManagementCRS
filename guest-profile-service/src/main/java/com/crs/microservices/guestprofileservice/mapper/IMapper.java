package com.crs.microservices.guestprofileservice.mapper;

import com.crs.microservices.guestprofileservice.dto.GuestDTO;
import com.crs.microservices.guestprofileservice.dto.StayDTO;
import com.crs.microservices.guestprofileservice.model.IGuest;
import com.crs.microservices.guestprofileservice.model.IHotel;
import com.crs.microservices.guestprofileservice.model.IStay;

public interface IMapper {
	public IGuest mapGuestDTOToIGuest(GuestDTO guestDTO);

    public GuestDTO mapIGuestToGuestDTO(IGuest iGuest);

    public StayDTO mapIStayToStayDTO(IStay iStay);

    public IHotel mapProxyHotelToIHotel(com.crs.microservices.guestprofileservice.proxy.model.IHotel proxyHotel);
}
