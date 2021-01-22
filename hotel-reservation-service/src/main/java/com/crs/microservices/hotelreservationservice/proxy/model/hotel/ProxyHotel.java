package com.crs.microservices.hotelreservationservice.proxy.model.hotel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = ProxyProxyHotelImpl.class)
public interface ProxyHotel {
    public Long getHotelId();

    public void setHotelId(Long hotelId);

    public String getName() ;

    public void setName(String name);

    public String getPhoneNumber();

    public void setPhoneNumber(String phoneNumber);

    public int getRating() ;

    public void setRating(int rating);

    public ProxyAddress getAddress();

    public void setAddress(ProxyAddress proxyAddress);
}
