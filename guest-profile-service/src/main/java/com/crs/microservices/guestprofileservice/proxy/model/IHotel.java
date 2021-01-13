package com.crs.microservices.guestprofileservice.proxy.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Hotel.class)
public interface IHotel {
	 public Long getHotelId();

	    public void setHotelId(Long hotelId);

	    public String getName();

	    public void setName(String name);

	    public String getPhoneNumber() ;

	    public void setPhoneNumber(String phoneNumber);

	    public int getStarRatting();

	    public void setStarRatting(int starRatting);

	    public IAddress getAddress();

	    public void setAddress(IAddress address);
}
