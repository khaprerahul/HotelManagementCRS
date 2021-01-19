package com.crs.microservices.proxy.model.hotel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = HotelImpl.class)
public interface Hotel {
    public Long getHotelId();

    public void setHotelId(Long hotelId);

    public String getName() ;

    public void setName(String name);

    public String getPhoneNumber();

    public void setPhoneNumber(String phoneNumber);

    public int getStarRatting() ;

    public void setStarRatting(int starRatting);

    public Address getAddress();

    public void setAddress(Address address);
}
